package com.dokang.base.entity;

import java.util.List;

/**
 * @author: YwT
 * @description: 分页返回实体
 * @create: 2018-12-28 17:49
 **/
public class PageResponseMessage<T> {
    private Long records;
    private Integer page;
    private Integer total;
    private List<T> rows;

    public PageResponseMessage() {
    }

    public PageResponseMessage(Long records, Integer page, Integer total, List rows) {
        this.records = records;
        this.page = page;
        this.total = total;
        this.rows = rows;
    }

    public static <T> PageResponseMessage<T> newInstance(Long records, Integer page, Integer total, List<T> rows) {
        return new PageResponseMessage(records, page, total, rows);
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
