package com.aiolos.comment.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Aiolos
 * @date 2019-12-02 21:07
 */
public class InformationCommentReq {

    @NotNull(message = "短消息ID不能为空")
    private Integer informationId;

    @NotEmpty(message = "评论内容不能为空")
    private String content;

    @NotNull(message = "评论者ID不能为空")
    private Integer fromUid;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }
}
