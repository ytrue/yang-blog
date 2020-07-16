package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Link;
import com.yang.blog.dto.BaseQueryParam;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/17 14:47
 * @description：
 */
public interface ILinkService extends IService<Link> {

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    ResponseData<Object> del(List<Long> ids);

    /**
     * 修改
     *
     * @param link
     * @param bindingResult
     * @return
     */
    ResponseData<Object> update1(Link link, BindingResult bindingResult);

    /**
     * id查找
     *
     * @param id
     * @return
     */
    ResponseData<Map<String, Object>> find(Long id);

    /**
     * 新增
     *
     * @param link
     * @param bindingResult
     * @return
     */
    ResponseData<Object> add(Link link, BindingResult bindingResult);

    /**
     * 分页查询
     *
     * @param queryCondition
     * @return
     */
    Map<String, Object> queryPage(BaseQueryParam queryCondition);
}
