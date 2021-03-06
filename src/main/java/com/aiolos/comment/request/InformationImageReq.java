package com.aiolos.comment.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Aiolos
 * @date 2019-11-28 23:16
 */
public class InformationImageReq {

    @NotNull(message = "短消息ID不能为空")
    private Integer informationId;

    @NotEmpty(message = "图片不能为空")
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
