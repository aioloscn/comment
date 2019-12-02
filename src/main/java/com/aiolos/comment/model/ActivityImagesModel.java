package com.aiolos.comment.model;

/**
 * Database Table Remarks:
 *   发布活动所添加的图片
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table campus_activity_images
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class ActivityImagesModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity_images.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   活动ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity_images.activity_id
     *
     * @mbg.generated
     */
    private Integer activityId;

    /**
     * Database Column Remarks:
     *   图片名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_activity_images.image
     *
     * @mbg.generated
     */
    private String image;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity_images.id
     *
     * @return the value of campus_activity_images.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity_images.id
     *
     * @param id the value for campus_activity_images.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity_images.activity_id
     *
     * @return the value of campus_activity_images.activity_id
     *
     * @mbg.generated
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity_images.activity_id
     *
     * @param activityId the value for campus_activity_images.activity_id
     *
     * @mbg.generated
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_activity_images.image
     *
     * @return the value of campus_activity_images.image
     *
     * @mbg.generated
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_activity_images.image
     *
     * @param image the value for campus_activity_images.image
     *
     * @mbg.generated
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}