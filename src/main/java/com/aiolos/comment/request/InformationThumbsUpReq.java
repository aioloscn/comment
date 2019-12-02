package com.aiolos.comment.request;

import javax.validation.constraints.NotNull;

/**
 * @author Aiolos
 * @date 2019-11-30 11:28
 */
public class InformationThumbsUpReq {

    @NotNull(message = "短消息来源ID不能为空")
    private Integer informationId;

    @NotNull(message = "点赞人ID不能为空")
    private Integer fromUid;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }
}
