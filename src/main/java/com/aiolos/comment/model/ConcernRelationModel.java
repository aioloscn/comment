package com.aiolos.comment.model;

import java.util.Date;

/**
 * Database Table Remarks:
 *   关注关联表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table campus_concern_relation
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class ConcernRelationModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_concern_relation.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   被关注者ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_concern_relation.follow_uid
     *
     * @mbg.generated
     */
    private Integer followUid;

    /**
     * Database Column Remarks:
     *   关注者ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_concern_relation.concern_uid
     *
     * @mbg.generated
     */
    private Integer concernUid;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_concern_relation.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_concern_relation.id
     *
     * @return the value of campus_concern_relation.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_concern_relation.id
     *
     * @param id the value for campus_concern_relation.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_concern_relation.follow_uid
     *
     * @return the value of campus_concern_relation.follow_uid
     *
     * @mbg.generated
     */
    public Integer getFollowUid() {
        return followUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_concern_relation.follow_uid
     *
     * @param followUid the value for campus_concern_relation.follow_uid
     *
     * @mbg.generated
     */
    public void setFollowUid(Integer followUid) {
        this.followUid = followUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_concern_relation.concern_uid
     *
     * @return the value of campus_concern_relation.concern_uid
     *
     * @mbg.generated
     */
    public Integer getConcernUid() {
        return concernUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_concern_relation.concern_uid
     *
     * @param concernUid the value for campus_concern_relation.concern_uid
     *
     * @mbg.generated
     */
    public void setConcernUid(Integer concernUid) {
        this.concernUid = concernUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_concern_relation.gmt_create
     *
     * @return the value of campus_concern_relation.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_concern_relation.gmt_create
     *
     * @param gmtCreate the value for campus_concern_relation.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}