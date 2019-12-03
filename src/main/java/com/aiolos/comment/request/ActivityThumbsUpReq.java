package com.aiolos.comment.request;

import javax.validation.constraints.NotNull;

/**
 * @author Aiolos
 * @date 2019-11-30 11:28
 */
public class ActivityThumbsUpReq {

    @NotNull(message = "活动来源ID不能为空")
    private Integer activityId;

    @NotNull(message = "点赞人ID不能为空")
    private Integer fromUid;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }
}
