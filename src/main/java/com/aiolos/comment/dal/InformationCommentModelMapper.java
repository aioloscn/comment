package com.aiolos.comment.dal;

import com.aiolos.comment.model.InformationCommentModel;

public interface InformationCommentModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_information_comment
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_information_comment
     *
     * @mbg.generated
     */
    int insert(InformationCommentModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_information_comment
     *
     * @mbg.generated
     */
    int insertSelective(InformationCommentModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_information_comment
     *
     * @mbg.generated
     */
    InformationCommentModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_information_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(InformationCommentModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus_information_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InformationCommentModel record);
}