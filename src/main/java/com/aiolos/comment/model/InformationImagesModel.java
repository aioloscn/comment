package com.aiolos.comment.model;

/**
 * Database Table Remarks:
 *   发布校报所添加的图片
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table campus_information_images
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class InformationImagesModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_images.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   校报ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_images.information_id
     *
     * @mbg.generated
     */
    private Integer informationId;

    /**
     * Database Column Remarks:
     *   图片名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column campus_information_images.image
     *
     * @mbg.generated
     */
    private String image;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_images.id
     *
     * @return the value of campus_information_images.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_images.id
     *
     * @param id the value for campus_information_images.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_images.information_id
     *
     * @return the value of campus_information_images.information_id
     *
     * @mbg.generated
     */
    public Integer getInformationId() {
        return informationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_images.information_id
     *
     * @param informationId the value for campus_information_images.information_id
     *
     * @mbg.generated
     */
    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column campus_information_images.image
     *
     * @return the value of campus_information_images.image
     *
     * @mbg.generated
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column campus_information_images.image
     *
     * @param image the value for campus_information_images.image
     *
     * @mbg.generated
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}