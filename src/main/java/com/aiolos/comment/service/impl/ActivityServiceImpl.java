package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.ActivityModelMapper;
import com.aiolos.comment.dal.ActivityThumbsUpModelMapper;
import com.aiolos.comment.dal.ActivityTopicModelMapper;
import com.aiolos.comment.model.ActivityModel;
import com.aiolos.comment.model.ActivityThumbsUpModel;
import com.aiolos.comment.model.ActivityTopicModel;
import com.aiolos.comment.service.ActivityService;
import com.aiolos.comment.utils.DateUtils;
import com.aiolos.comment.vo.ActivityVO;
import com.aiolos.comment.vo.CommonPageVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Aiolos
 * @date 2019-12-03 05:00
 */
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {

    @Value("${image.url}")
    private String imageUrl;

    @Autowired
    private RestHighLevelClient highLevelClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActivityModelMapper activityModelMapper;

    @Autowired
    private ActivityThumbsUpModelMapper activityThumbsUpModelMapper;

    @Autowired
    private ActivityTopicModelMapper activityTopicModelMapper;

    @Override
    public CommonResponse getActivity(int topicId, Integer userId, int pageIndex, int pageCount) {

        long startTime = System.currentTimeMillis();
        List<ActivityVO> list = activityModelMapper.selectAllActivity(topicId, pageIndex, pageCount);

        int rowsTotal = 0;
        List<String> imageList = null;
        if (list != null && list.size() > 0) {
            rowsTotal = list.get(0).getRowsTotal();
            for (ActivityVO activityVO : list) {
                String images = activityVO.getImages();
                // 拼接图片地址
                if (StringUtils.isNotBlank(images)) {
                    imageList = Arrays.asList(images.split(","));
                    for (int i = 0; i < imageList.size(); i++) {
                        imageList.set(i, imageUrl + imageList.get(i));
                    }
                    activityVO.setImageList(imageList);
                    activityVO.setFirstImage(imageList.get(0));
                    activityVO.setPlaintext(false);
                } else {
                    activityVO.setPlaintext(true);
                }
                // 将字符还原成emoji
                if (StringUtils.isNotEmpty(activityVO.getContent())) {
                    String content = EmojiParser.parseToUnicode(activityVO.getContent());
                    activityVO.setContent(content);
                }

                if (StringUtils.isNotEmpty(activityVO.getNickname())) {
                    String nickname = EmojiParser.parseToUnicode(activityVO.getNickname());
                    activityVO.setNickname(nickname);
                }

                if (StringUtils.isNotEmpty(activityVO.getTitle())) {
                    String title = EmojiParser.parseToUnicode(activityVO.getTitle());
                    activityVO.setTitle(title);
                }
                // 转换时间
                activityVO.setDateInterval(DateUtils.toToday(activityVO.getGmtCreate()));

                // 赋值是否已点赞
                if (userId != null && redisTemplate.opsForSet().isMember(Constant.ACTIVITYPRAISEDREDISKEY + userId, activityVO.getId())) {
                    activityVO.setPraised(true);
                }
            }
        }

        int pages = rowsTotal == 0 ? 0 : new Double(Math.ceil(Double.valueOf(rowsTotal) / pageCount)).intValue();
        CommonPageVO<List<ActivityVO>> commonPageVO = new CommonPageVO<>(pages, list);
        log.info("function selectAllActivity execution time: {}", System.currentTimeMillis() - startTime);
        return CommonResponse.ok(commonPageVO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse releaseContent(ActivityModel activityModel) {

        int affected = activityModelMapper.insertSelective(activityModel);
        if (affected > 0) {
            return CommonResponse.ok("已发布", activityModel.getId());
        }
        return CommonResponse.error(EnumError.ACTIVITY_RELEASE_FAIL.getErrCode(), EnumError.ACTIVITY_RELEASE_FAIL.getErrMsg());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse deleteActivity(Integer id) {
        int affected = activityModelMapper.deleteByPrimaryKey(id);
        if (affected > 0) {
            return CommonResponse.ok("已删除, null)");
        }
        return CommonResponse.error(EnumError.DELETE_FAIL.getErrCode(), EnumError.DELETE_FAIL.getErrMsg());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public CommonResponse thumbsUp(ActivityThumbsUpModel activityThumbsUpModel) throws CustomizeException {

        // TODO 后续引入消息队列，先更新redis再异步更新到数据库
        int affected = 0;
        try {
            affected = activityThumbsUpModelMapper.insertSelective(activityThumbsUpModel);
        } catch (DuplicateKeyException e) {
            log.error("违反activity_thumbs_up唯一约束");
            return CommonResponse.error(EnumError.CONSTRAINT_VIOLATION.getErrCode(), EnumError.CONSTRAINT_VIOLATION.getErrMsg());
        }
        affected += activityModelMapper.increaseThumbsUp(activityThumbsUpModel.getActivityId());
        if (affected == 2) {

            // 将被点赞的信息ID添加到当前用户的redis集合中
            redisTemplate.opsForSet().add(Constant.THUMBSUPREDISKEY + activityThumbsUpModel.getFromUid(), activityThumbsUpModel.getActivityId());
            return CommonResponse.ok(null);
        } else {
            throw new RuntimeException(EnumError.THUMBS_UP_FAIL.getErrMsg());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public CommonResponse cancelThumbsUp(ActivityThumbsUpModel activityThumbsUpModel) throws CustomizeException {

        // TODO 后续引入消息队列，先更新redis再异步更新到数据库
        int resultCount = activityThumbsUpModelMapper.selectByModel(activityThumbsUpModel);
        if (resultCount == 0)
            return CommonResponse.error(EnumError.DUPLICATION_OPERATION.getErrCode(), EnumError.DUPLICATION_OPERATION.getErrMsg());

        int affected = activityThumbsUpModelMapper.cancelThumbsUp(activityThumbsUpModel);
        affected += activityModelMapper.decreaseThumbsUp(activityThumbsUpModel.getActivityId());
        if (affected == 2) {

            // 将取消点赞的信息ID从当前用户的redis集合中移除
            redisTemplate.opsForSet().remove(Constant.THUMBSUPREDISKEY + activityThumbsUpModel.getFromUid(), activityThumbsUpModel.getActivityId());
            return CommonResponse.ok(null);
        } else {
            throw new RuntimeException(EnumError.CANCEL_THUMBS_UP_FAIL.getErrMsg());
        }
    }

    @Override
    public List<ActivityTopicModel> getTopics() {
        return activityTopicModelMapper.selectAllTopics();
    }

    @Override
    public CommonResponse search(int topicId, Integer userId, String keyword, int pageIndex, int pageCount) throws CustomizeException {

        /*SearchRequest searchRequest = new SearchRequest("activity");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("content", keyword));
        searchSourceBuilder.timeout(new TimeValue(10, TimeUnit.SECONDS));
        SearchResponse searchResponse = null;
        try {
            searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                hit.getSourceAsMap();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new CustomizeException(EnumError.ES_SEARCH_ERROR);
        }*/

        Request request = new Request("GET", "/activity/_search");
        String reqJson = "";
        log.info("ES request: {}", reqJson);
        request.setJsonEntity(reqJson);
        try {
            Response response = highLevelClient.getLowLevelClient().performRequest(request);
            String responseStr = EntityUtils.toString(response.getEntity());
            log.info("ES response: {}", responseStr);
            JSONObject jsonObject = JSONObject.parseObject(responseStr);
            JSONArray jsonArray = jsonObject.getJSONObject("hits").getJSONArray("hits");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Integer id = Integer.parseInt(object.getString("_id"));
                BigDecimal distance = new BigDecimal(object.getJSONObject("fields").getJSONArray("distance").get(0).toString());

            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new CustomizeException(EnumError.ES_SEARCH_ERROR);
        }

        return CommonResponse.ok(null);
    }
}
