package com.aiolos.comment.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Aiolos
 * @date 2019-11-30 07:46
 */
public class ConcernRelationReq {

    @NotNull(message = "被关注者ID不能为空")
    private Integer followUid;

    @NotNull(message = "关注者ID不能为空")
    private Integer concernUid;

    public Integer getFollowUid() {
        return followUid;
    }

    public void setFollowUid(Integer followUid) {
        this.followUid = followUid;
    }

    public Integer getConcernUid() {
        return concernUid;
    }

    public void setConcernUid(Integer concernUid) {
        this.concernUid = concernUid;
    }
}
