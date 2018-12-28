package com.dokang.base.web;

import com.dokang.base.entity.BaseEntity;
import com.dokang.base.entity.PageRequestMessage;
import com.dokang.base.entity.ResponseMessage;
import com.dokang.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author: YwT
 * @create: 2018-12-28 18:00
 **/
public class DefaultBaseControllerImpl<T extends BaseEntity, S extends BaseService<T>> implements BaseController<T> {

    @Autowired
    protected S service;

    @Override
    @PostMapping(value = "get/{id}")
    public ResponseMessage getById(@PathVariable String id) {
        return ResponseMessage.newOkInstance(service.getById(id));
    }

    @Override
    @PostMapping(value = "query")
    public ResponseMessage get(@RequestBody T data) {
        return ResponseMessage.newOkInstance(service.getById(data));
    }

    @Override
    @PostMapping(value = "delete/{id}")
    public ResponseMessage deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseMessage.newOkInstance(id);
    }

    @Override
    @PostMapping(value = "remove")
    public ResponseMessage delete(@RequestBody T data) {
        service.delete(data);
        return ResponseMessage.newOkInstance(data);
    }

    @Override
    @PostMapping(value = "insert")
    public ResponseMessage insert(@RequestBody T data) {
        return ResponseMessage.newOkInstance(service.insert(data));
    }

    @Override
    @PostMapping(value = "update")
    public ResponseMessage update(@RequestBody T data) {

        return ResponseMessage.newOkInstance(service.update(data));
    }

    @Override
    @PostMapping(value = "insertOrUpdate")
    public ResponseMessage insertOrUpdate(@RequestBody T data) {
        return ResponseMessage.newOkInstance(service.insertOrUpdate(data));
    }

    @PostMapping(value = "list")
    public ResponseMessage getListPage(@RequestBody PageRequestMessage page) {
        Map<String, Object> param = page.getQueryParam();
        return ResponseMessage.newOkInstance(service.getList(param, page));
    }
}
