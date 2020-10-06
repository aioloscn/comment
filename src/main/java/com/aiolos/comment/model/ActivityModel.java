package com.aiolos.comment.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Database Table Remarks:
 *   活动表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table campus_activity
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class ActivityModel {
    /**
     * Database Column Remarks:
     *   10位无符号数字，唯一自增长
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   活动发起人ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.from_uid
     *
     * @mbg.generated
     */
    private Integer fromUid;

    /**
     * Database Column Remarks:
     *   主题ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.topic_id
     *
     * @mbg.generated
     */
    private Integer topicId;

    /**
     * Database Column Remarks:
     *   标题
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     * Database Column Remarks:
     *   省份
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.province
     *
     * @mbg.generated
     */
    private String province;

    /**
     * Database Column Remarks:
     *   城市
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.city
     *
     * @mbg.generated
     */
    private String city;

    /**
     * Database Column Remarks:
     *   县区
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.county
     *
     * @mbg.generated
     */
    private String county;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * Database Column Remarks:
     *   是否置顶，0：不置顶，1：置顶
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.top
     *
     * @mbg.generated
     */
    private Byte top;

    /**
     * Database Column Remarks:
     *   是否为热门，0：不是热门，1：是热门
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.hot
     *
     * @mbg.generated
     */
    private Byte hot;

    /**
     * Database Column Remarks:
     *   活动报名数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.sign_up_num
     *
     * @mbg.generated
     */
    private Integer signUpNum;

    /**
     * Database Column Remarks:
     *   点赞数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.thumbs_up_num
     *
     * @mbg.generated
     */
    private Integer thumbsUpNum;

    /**
     * Database Column Remarks:
     *   评论数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.comment_num
     *
     * @mbg.generated
     */
    private Integer commentNum;

    /**
     * Database Column Remarks:
     *   活动状态，-2：被强制关闭，-1：已删除，0：已发布，1：暂停，2：已结束
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * Database Column Remarks:
     *   权重，按发布时间+评论数+点赞数排序，新发布的活动排在点赞数+评论数小于30前面，权重值每6分钟减1
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.weight
     *
     * @mbg.generated
     */
    private Integer weight;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     * Database Column Remarks:
     *   活动内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.id
     *
     * @return the value of campus_activity.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.id
     *
     * @param id the value for campus_activity.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.from_uid
     *
     * @return the value of campus_activity.from_uid
     *
     * @mbg.generated
     */
    public Integer getFromUid() {
        return fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.from_uid
     *
     * @param fromUid the value for campus_activity.from_uid
     *
     * @mbg.generated
     */
    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.topic_id
     *
     * @return the value of campus_activity.topic_id
     *
     * @mbg.generated
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.topic_id
     *
     * @param topicId the value for campus_activity.topic_id
     *
     * @mbg.generated
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.title
     *
     * @return the value of campus_activity.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.title
     *
     * @param title the value for campus_activity.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.province
     *
     * @return the value of campus_activity.province
     *
     * @mbg.generated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.province
     *
     * @param province the value for campus_activity.province
     *
     * @mbg.generated
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.city
     *
     * @return the value of campus_activity.city
     *
     * @mbg.generated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.city
     *
     * @param city the value for campus_activity.city
     *
     * @mbg.generated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.county
     *
     * @return the value of campus_activity.county
     *
     * @mbg.generated
     */
    public String getCounty() {
        return county;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.county
     *
     * @param county the value for campus_activity.county
     *
     * @mbg.generated
     */
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.top
     *
     * @return the value of campus_activity.top
     *
     * @mbg.generated
     */
    public Byte getTop() {
        return top;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.top
     *
     * @param top the value for campus_activity.top
     *
     * @mbg.generated
     */
    public void setTop(Byte top) {
        this.top = top;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.hot
     *
     * @return the value of campus_activity.hot
     *
     * @mbg.generated
     */
    public Byte getHot() {
        return hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.hot
     *
     * @param hot the value for campus_activity.hot
     *
     * @mbg.generated
     */
    public void setHot(Byte hot) {
        this.hot = hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.sign_up_num
     *
     * @return the value of campus_activity.sign_up_num
     *
     * @mbg.generated
     */
    public Integer getSignUpNum() {
        return signUpNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.sign_up_num
     *
     * @param signUpNum the value for campus_activity.sign_up_num
     *
     * @mbg.generated
     */
    public void setSignUpNum(Integer signUpNum) {
        this.signUpNum = signUpNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.thumbs_up_num
     *
     * @return the value of campus_activity.thumbs_up_num
     *
     * @mbg.generated
     */
    public Integer getThumbsUpNum() {
        return thumbsUpNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.thumbs_up_num
     *
     * @param thumbsUpNum the value for campus_activity.thumbs_up_num
     *
     * @mbg.generated
     */
    public void setThumbsUpNum(Integer thumbsUpNum) {
        this.thumbsUpNum = thumbsUpNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.comment_num
     *
     * @return the value of campus_activity.comment_num
     *
     * @mbg.generated
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.comment_num
     *
     * @param commentNum the value for campus_activity.comment_num
     *
     * @mbg.generated
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.status
     *
     * @return the value of campus_activity.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.status
     *
     * @param status the value for campus_activity.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.weight
     *
     * @return the value of campus_activity.weight
     *
     * @mbg.generated
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.weight
     *
     * @param weight the value for campus_activity.weight
     *
     * @mbg.generated
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.gmt_create
     *
     * @return the value of campus_activity.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.gmt_create
     *
     * @param gmtCreate the value for campus_activity.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.gmt_modified
     *
     * @return the value of campus_activity.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.gmt_modified
     *
     * @param gmtModified the value for campus_activity.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity.content
     *
     * @return the value of campus_activity.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity.content
     *
     * @param content the value for campus_activity.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}