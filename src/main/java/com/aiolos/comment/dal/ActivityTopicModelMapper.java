package com.aiolos.comment.dal;

import com.aiolos.comment.model.ActivityTopicModel;

public interface ActivityTopicModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_topic
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_topic
     *
     * @mbg.generated
     */
    int insert(ActivityTopicModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_topic
     *
     * @mbg.generated
     */
    int insertSelective(ActivityTopicModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_topic
     *
     * @mbg.generated
     */
    ActivityTopicModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_topic
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ActivityTopicModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_topic
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ActivityTopicModel record);
}