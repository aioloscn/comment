package com.aiolos.comment.dal;

import com.aiolos.comment.model.ActivityCommentThumbsUpModel;

public interface ActivityCommentThumbsUpModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_comment_thumbs_up
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_comment_thumbs_up
     *
     * @mbg.generated
     */
    int insert(ActivityCommentThumbsUpModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_comment_thumbs_up
     *
     * @mbg.generated
     */
    int insertSelective(ActivityCommentThumbsUpModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_comment_thumbs_up
     *
     * @mbg.generated
     */
    ActivityCommentThumbsUpModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_comment_thumbs_up
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ActivityCommentThumbsUpModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_activity_comment_thumbs_up
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ActivityCommentThumbsUpModel record);
}