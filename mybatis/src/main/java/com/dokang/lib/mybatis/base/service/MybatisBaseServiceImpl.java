package com.dokang.lib.mybatis.base.service;

import com.dokang.lib.base.entity.BaseEntity;
import com.dokang.lib.base.entity.PageRequestMessage;
import com.dokang.lib.base.entity.PageResponseMessage;
import com.dokang.lib.base.service.BaseService;
import com.dokang.lib.mybatis.base.dao.MybatisBaseRepository;
import com.dokang.lib.mybatis.page.MessagePageHelper;
import com.dokang.lib.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: YwT
 * @description: service 基类，实现crud假删除
 * @create: 2018-12-28 21:25
 **/
public abstract class MybatisBaseServiceImpl<T extends BaseEntity, ID extends Serializable, R extends
        MybatisBaseRepository<T, ID>> implements BaseService<T> {

    @Autowired
    protected R repository;

    protected Class entityClass;

    public MybatisBaseServiceImpl() {
        entityClass = ReflectionUtils.getClassGenricType(getClass(), 0);
    }

    /**
     * 根据id获取实体
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public T getById(Object id) {
        if (preGet(id)) {
            T data = repository.selectByPrimaryKey(id);
            afterGet(data);
            return data;
        }
        return null;
    }

    /**
     * 根据一系列id查询，只适用与单主键表
     *
     * @param idFieldName
     * @param ids
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> getByIds(String idFieldName, List<T> ids) {
        Example example = new Example(entityClass);
        Example.Criteria cr = example.createCriteria();
        cr.andIn(idFieldName, ids);
        return getListByExample(example);
    }


    @Transactional
    @Override
    public T insertOrUpdate(T data) {
        Object key = getKey(data);
        if (key != null && getById(key) != null) {
            return update(data);
        } else {
            return insert(data);
        }
    }


    @Transactional
    @Override
    public T insert(T data) {
        if (StringUtils.isBlank(data.getIsDelete())) {
            data.setIsDelete(BaseEntity.FLAG_FALSE);
        }
        if (preInsert(data)) {
//            repository.insert(data);
            // 记录添加数据时间
            data.setCreatedTime(new Date());
            repository.insertSelective(data);
            afterInsert(data);
            return data;
        }
        return null;
    }

    @Transactional
    @Override
    public T update(T data) {
        if (StringUtils.isBlank(data.getIsDelete())) {
            data.setIsDelete(BaseEntity.FLAG_FALSE);
        }

        if (preUpdate(data)) {
//            repository.updateByPrimaryKey(data);
            // 记录更新数据时间
            data.setUpdateTime(new Date());
            repository.updateByPrimaryKeySelective(data);
            afterUpdate(data);
            return data;
        }
        return null;
    }

    /**
     * 删除数据，基类中有删除标志，所以这里面的删除是假删除数据库数据
     *
     * @param data
     */
    @Transactional
    @Override
    public void delete(T data) {
        data.setIsDelete(BaseEntity.FLAG_TRUE);
        if (preDelete(data)) {
            data.setUpdateTime(new Date());
            repository.updateByPrimaryKey(data);
            afterDelete(data);
        }

    }

    /**
     * 根据ID删除数据，基类中有删除标志，所以这里面的删除是假删除数据库数据
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteById(Object id) {
        if (preDelete(id)) {
            T data = getById(id);
            if (data != null) {
                data.setIsDelete(BaseEntity.FLAG_TRUE);
                data.setUpdateTime(new Date());
                repository.updateByPrimaryKey(data);
                afterDelete(data);
            }
        }
    }

    @Transactional
    @Override
    public void unDelete(T data) {
        data.setIsDelete(BaseEntity.FLAG_FALSE);
        data.setUpdateTime(new Date());
        repository.updateByPrimaryKeySelective(data);
    }

    @Transactional
    @Override
    public void unDeleteById(Object id) {
        T data = getById(id);
        if (data != null) {
            data.setIsDelete(BaseEntity.FLAG_FALSE);
            data.setUpdateTime(new Date());
            repository.updateByPrimaryKey(data);
        }
    }

    @Override
    public List<T> getList(Map<String, Object> param) {
        Example example = (Example) buildQueryExample(param);
        Example.Criteria cr = null;
        if (example == null) {
            example = new Example(entityClass);
            cr = example.createCriteria();
        } else {
            cr = example.getOredCriteria().size() > 0 ? example.getOredCriteria().get(0) : example.createCriteria();
        }
        cr.andEqualTo(BaseEntity.DELETE_FIELD_NAME, BaseEntity.FLAG_FALSE);
        if (example != null) {
            return getListByExample(example);
        } else {
            return repository.selectAll();
        }
    }

    @Override
    public PageResponseMessage<T> getList(Map<String, Object> param, PageRequestMessage page) {
        MessagePageHelper.startPage(page);
        return MessagePageHelper.parseResult(getList(param));
    }

    public List<T> getListByExample(Example example) {
        return repository.selectByExample(example);
    }

    public PageResponseMessage<T> getListByExample(Example example, PageRequestMessage page) {
        MessagePageHelper.startPage(page);
        return MessagePageHelper.parseResult(repository.selectByExample(example));
    }

    public abstract Object buildQueryExample(Map<String, Object> param);


    @Override
    public Object getKey(T data) {
        Object key = null;
        List<Field> idFields = ReflectionUtils.getFieldsByAnnotation(javax.persistence.Id.class, data);
        if (idFields != null && idFields.size() > 0) {
            if (idFields.size() == 1) {
                key = ReflectionUtils.invokeGetter(data, idFields.get(0).getName());
            } else {
                HashMap<String, Object> keyMap = new HashMap<String, Object>();
                for (Field item : idFields) {
                    keyMap.put(item.getName(), ReflectionUtils.invokeGetter(data, item.getName()));
                }
                key = keyMap;
            }
        } else {
            key = ReflectionUtils.invokeGetter(data, "id");
        }
        return key;
    }


    /**
     * 设置实体主键<br>
     * 如果是单一主键直接传入主键，如果是复合主键<br>
     * 将多个主键封装成Map<String,Object> 的形式传入<br>
     * 注：该方法使用javax.persistence.Id判断是否主键
     *
     * @param data
     * @param key
     * @return
     */
    @Override
    public Object setKey(T data, Object key) {
        // TODO Auto-generated method stub
        List<Field> idFields = ReflectionUtils.getFieldsByAnnotation(Id.class, data);
        if (idFields != null && idFields.size() > 0) {
            if (idFields.size() == 1) {
                ReflectionUtils.invokeSetter(data, idFields.get(0).getName(), key);
            } else {

                if (key instanceof Map) {
                    Map<String, Object> keyMap = (Map<String, Object>) key;
                    for (Field item : idFields) {
                        ReflectionUtils.invokeSetter(data, item.getName(), keyMap.get(item.getName()));
                    }
                }
            }
        } else {
            ReflectionUtils.invokeSetter(data, "id", key);
        }
        return key;
    }


    @Override
    public boolean preGet(Object id) {
        return true;
    }

    @Override
    public void afterGet(T data) {

    }

    @Override
    public boolean preInsert(T data) {
        return true;
    }

    @Override
    public void afterInsert(T data) {

    }

    @Override
    public boolean preUpdate(T data) {
        return true;
    }

    @Override
    public void afterUpdate(T data) {

    }

    @Override
    public boolean preDelete(Object id) {
        return true;
    }

    @Override
    public void afterDelete(Object id) {

    }

}
