package com.aiolos.comment.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Aiolos
 * @date 2019-11-28 23:16
 */
public class ActivityImageReq {

    @NotNull(message = "活动ID不能为空")
    private Integer activityId;

    @NotEmpty(message = "图片不能为空")
    private String image;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
