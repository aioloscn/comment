package com.aiolos.comment.vo;

import java.io.Serializable;

/**
 * @author Aiolos
 * @date 2019-11-29 19:26
 */
public class CommonPageVO<T> implements Serializable {

    private Integer pages;

    private T obj;

    public CommonPageVO() {

    }

    public CommonPageVO(Integer pages, T obj) {
        this.pages = pages;
        this.obj = obj;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
