package com.aiolos.comment.request;

/**
 * @author Aiolos
 * @date 2019-11-28 23:16
 */
public class InformationImageReq {

    private Integer informationId;

    private String image;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
