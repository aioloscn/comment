package com.aiolos.comment.vo;

import java.util.Date;

/**
 * @author Aiolos
 * @date 2019-12-03 02:06
 */
public class InformationCommentVO {

    private Integer id;

    private Integer informationId;

    private String content;

    private String nickname;

    private String headPortrait;

    private Integer hot;

    private Integer thumbsUpNum;

    private Integer comentNum;

    private Integer status;

    private Date gmtCreate;

    /**
     * 当前用户对于该条评论是否已点赞
     */
    private boolean praised;

    private Integer rowsTotal;

    private String dateInterval;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getThumbsUpNum() {
        return thumbsUpNum;
    }

    public void setThumbsUpNum(Integer thumbsUpNum) {
        this.thumbsUpNum = thumbsUpNum;
    }

    public Integer getComentNum() {
        return comentNum;
    }

    public void setComentNum(Integer comentNum) {
        this.comentNum = comentNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public boolean isPraised() {
        return praised;
    }

    public void setPraised(boolean praised) {
        this.praised = praised;
    }

    public Integer getRowsTotal() {
        return rowsTotal;
    }

    public void setRowsTotal(Integer rowsTotal) {
        this.rowsTotal = rowsTotal;
    }

    public String getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(String dateInterval) {
        this.dateInterval = dateInterval;
    }
}
