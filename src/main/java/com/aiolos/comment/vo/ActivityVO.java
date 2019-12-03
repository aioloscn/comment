package com.aiolos.comment.vo;

import java.util.Date;
import java.util.List;

/**
 * @author Aiolos
 * @date 2019-12-03 05:01
 */
public class ActivityVO {

    private Integer id;

    private Integer fromUid;

    private String nickname;

    private String headPortrait;

    private String content;

    private Integer topicId;

    private String province;

    private String city;

    private String county;

    private Integer thumbsUpNum;

    private Integer commentNum;

    private Integer status;

    private Date gmtCreate;

    private String dateInterval;

    private String topicName;

    private Integer type;

    private String images;

    private List<String> imageList;

    /**
     * 当前用户对于该条短消息是否已点赞
     */
    private boolean praised;

    private Integer rowsTotal;

    private Boolean plaintext;

    private String firstImage;

    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getThumbsUpNum() {
        return thumbsUpNum;
    }

    public void setThumbsUpNum(Integer thumbsUpNum) {
        this.thumbsUpNum = thumbsUpNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(String dateInterval) {
        this.dateInterval = dateInterval;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public boolean isPraised() {
        return praised;
    }

    public void setPraised(boolean praised) {
        this.praised = praised;
    }

    public Integer getRowsTotal() {
        return rowsTotal;
    }

    public void setRowsTotal(Integer rowsTotal) {
        this.rowsTotal = rowsTotal;
    }

    public Boolean getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(Boolean plaintext) {
        this.plaintext = plaintext;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
