package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Comment;
import com.yang.blog.dto.BaseQueryParam;
import com.yang.blog.util.ResponseData;

import java.util.List;
import java.util.Map;

public interface ICommentService extends IService<Comment> {
    /**
     * 分页查询
     * @param queryCondition
     * @return
     */
    Map<String, Object> queryPage(BaseQueryParam queryCondition);

    /**
     * id删除
     * @param ids
     * @return
     */
    ResponseData<Object> del(List<Long> ids);

    /**
     * 审核
     * @param ids
     * @return
     */
    ResponseData<Object> audit(List<Long> ids);

    /**
     * 回复
     * @param comment
     * @return
     */
    ResponseData<Object> reply(Comment comment);
}
