package com.aiolos.comment.model;

import java.util.Date;

/**
 * Database Table Remarks:
 *   活动点赞关联表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table campus_activity_thumbs_up
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class ActivityThumbsUpModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity_thumbs_up.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   活动ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity_thumbs_up.activity_id
     *
     * @mbg.generated
     */
    private Integer activityId;

    /**
     * Database Column Remarks:
     *   活动点赞人ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity_thumbs_up.from_uid
     *
     * @mbg.generated
     */
    private Integer fromUid;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity_thumbs_up.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity_thumbs_up.id
     *
     * @return the value of campus_activity_thumbs_up.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity_thumbs_up.id
     *
     * @param id the value for campus_activity_thumbs_up.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity_thumbs_up.activity_id
     *
     * @return the value of campus_activity_thumbs_up.activity_id
     *
     * @mbg.generated
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity_thumbs_up.activity_id
     *
     * @param activityId the value for campus_activity_thumbs_up.activity_id
     *
     * @mbg.generated
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity_thumbs_up.from_uid
     *
     * @return the value of campus_activity_thumbs_up.from_uid
     *
     * @mbg.generated
     */
    public Integer getFromUid() {
        return fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity_thumbs_up.from_uid
     *
     * @param fromUid the value for campus_activity_thumbs_up.from_uid
     *
     * @mbg.generated
     */
    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity_thumbs_up.gmt_create
     *
     * @return the value of campus_activity_thumbs_up.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity_thumbs_up.gmt_create
     *
     * @param gmtCreate the value for campus_activity_thumbs_up.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}