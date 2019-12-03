package com.aiolos.comment.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Aiolos
 * @date 2019-11-28 02:02
 */
public class ActivityReq {

    @NotNull(message = "发布者不能为空")
    private Integer fromUid;

    private String title;

    @NotEmpty(message = "内容不能为空")
    private String content;

    private Integer topicId;

    private String province;

    private String city;

    private String county;

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(" fromUid: ").append(fromUid)
                .append(" content: ").append(content)
                .append(" topicId: ").append(topicId)
                .append(" province: ").append(province)
                .append(" city: ").append(city)
                .append(" county: ").append(county);
        return sb.toString();
    }
}
