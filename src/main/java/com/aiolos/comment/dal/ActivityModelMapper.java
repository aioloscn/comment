package com.aiolos.comment.dal;

import com.aiolos.comment.model.ActivityModel;

public interface ActivityModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity
     *
     * @mbg.generated
     */
    int insert(ActivityModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity
     *
     * @mbg.generated
     */
    int insertSelective(ActivityModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity
     *
     * @mbg.generated
     */
    ActivityModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ActivityModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(ActivityModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ActivityModel record);
}