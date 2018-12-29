package com.dokang.lib.base.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: YwT
 * @description: 分页参数实体
 * @create: 2018-12-28 17:51
 **/
public class PageRequestMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer page;
    private Integer rows;
    private String sidx;
    private String sord;
    private Map<String, Object> queryParam;

    public PageRequestMessage() {
    }

    public PageRequestMessage(Integer page, Integer rows, String sidx, String sord, Map<String, Object> queryParam) {
        this.page = page;
        this.rows = rows;
        this.sidx = sidx;
        this.sord = sord;
        this.queryParam = queryParam;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Map<String, Object> getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(Map<String, Object> queryParam) {
        this.queryParam = queryParam;
    }
}
