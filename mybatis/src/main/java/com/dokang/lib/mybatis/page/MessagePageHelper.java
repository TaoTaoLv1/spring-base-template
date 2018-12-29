package com.dokang.lib.mybatis.page;

import com.dokang.lib.base.entity.PageRequestMessage;
import com.dokang.lib.base.entity.PageResponseMessage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

/**
 * 支持jqgrid格式的pageHelper
 *
 * @author jiabin
 **/
public class MessagePageHelper extends PageHelper {
    public static final Logger log = LoggerFactory.getLogger(MessagePageHelper.class);
    public static final String[] pageParam = {"page", "rows", "sidx", "sord", "_search", "nd"};

    /**
     * only for oracle && dm
     *
     * @param req
     * @param nullsControl
     */
    public static void startPage(PageRequestMessage req, String nullsControl) {
        if (!req.getSord().equalsIgnoreCase("desc")) {
            req.setSord(req.getSord());
        } else {
            req.setSord(req.getSord() + " " + nullsControl);
        }

        startPage(req);
    }


    public static void startPage(PageRequestMessage req) {
        if (!StringUtils.isBlank(req.getSidx()) && !StringUtils.isBlank(req.getSord())) {
            orderBy(req.getSidx() + " " + req.getSord());
        }
        startPage(req.getPage(), req.getRows(), true);
    }

    public static <T> PageResponseMessage parseResult(List<T> queryResult) {
        PageResponseMessage res = new PageResponseMessage();
        if (queryResult instanceof Page) {
            log.info("queryResult is object of page");
            Page<T> temp = (Page<T>) queryResult;
            res.setPage(temp.getPageNum());
            res.setRecords(temp.getTotal());
            res.setTotal(temp.getPages());
            res.setRows(temp.getResult());
        } else if (queryResult instanceof Collection) {
            log.info("queryResult is object of Collection");
//			Page<T> temp = new Page<T>();
//			for (T record : queryResult){
//				temp.add(record);
//			}
//			res.setPage(temp.getPageNum());
//			res.setRecords(temp.getTotal());
//			res.setTotal(temp.getPages());
//			res.setRows(temp.getResult());
        }
        return res;
    }
}
