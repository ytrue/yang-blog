package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Tag;
import com.yang.blog.dto.QueryParam;
import com.yang.blog.util.ResponseData;

import java.util.List;
import java.util.Map;

public interface ITagService extends IService<Tag> {

    /**
     * 分页查询
     *
     * @param queryCondition
     * @return
     */
    Map<String, Object> queryPage(QueryParam queryCondition);

    /**
     * id删除
     *
     * @param ids
     * @return
     */
    ResponseData<Object> del(List<Long> ids);
}
