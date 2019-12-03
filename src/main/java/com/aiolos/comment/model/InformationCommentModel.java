package com.aiolos.comment.model;

import java.util.Date;

/**
 * Database Table Remarks:
 *   校报评论消息表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table campus_information_comment
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class InformationCommentModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   校报ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.information_id
     *
     * @mbg.generated
     */
    private Integer informationId;

    /**
     * Database Column Remarks:
     *   评论内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * Database Column Remarks:
     *   评论者ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.from_uid
     *
     * @mbg.generated
     */
    private Integer fromUid;

    /**
     * Database Column Remarks:
     *   是否为热评，0：不是热评，1：是热评
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.hot
     *
     * @mbg.generated
     */
    private Byte hot;

    /**
     * Database Column Remarks:
     *   评论被点赞数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.thumbs_up_num
     *
     * @mbg.generated
     */
    private Integer thumbsUpNum;

    /**
     * Database Column Remarks:
     *   评论被评论数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.comment_num
     *
     * @mbg.generated
     */
    private Integer commentNum;

    /**
     * Database Column Remarks:
     *   评论状态，-2：被校报发起人删除，-1：自己删除，1：正常
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_comment.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.id
     *
     * @return the value of campus_information_comment.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.id
     *
     * @param id the value for campus_information_comment.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.information_id
     *
     * @return the value of campus_information_comment.information_id
     *
     * @mbg.generated
     */
    public Integer getInformationId() {
        return informationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.information_id
     *
     * @param informationId the value for campus_information_comment.information_id
     *
     * @mbg.generated
     */
    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.content
     *
     * @return the value of campus_information_comment.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.content
     *
     * @param content the value for campus_information_comment.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.from_uid
     *
     * @return the value of campus_information_comment.from_uid
     *
     * @mbg.generated
     */
    public Integer getFromUid() {
        return fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.from_uid
     *
     * @param fromUid the value for campus_information_comment.from_uid
     *
     * @mbg.generated
     */
    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.hot
     *
     * @return the value of campus_information_comment.hot
     *
     * @mbg.generated
     */
    public Byte getHot() {
        return hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.hot
     *
     * @param hot the value for campus_information_comment.hot
     *
     * @mbg.generated
     */
    public void setHot(Byte hot) {
        this.hot = hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.thumbs_up_num
     *
     * @return the value of campus_information_comment.thumbs_up_num
     *
     * @mbg.generated
     */
    public Integer getThumbsUpNum() {
        return thumbsUpNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.thumbs_up_num
     *
     * @param thumbsUpNum the value for campus_information_comment.thumbs_up_num
     *
     * @mbg.generated
     */
    public void setThumbsUpNum(Integer thumbsUpNum) {
        this.thumbsUpNum = thumbsUpNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.comment_num
     *
     * @return the value of campus_information_comment.comment_num
     *
     * @mbg.generated
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.comment_num
     *
     * @param commentNum the value for campus_information_comment.comment_num
     *
     * @mbg.generated
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.status
     *
     * @return the value of campus_information_comment.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.status
     *
     * @param status the value for campus_information_comment.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_comment.gmt_create
     *
     * @return the value of campus_information_comment.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_comment.gmt_create
     *
     * @param gmtCreate the value for campus_information_comment.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}