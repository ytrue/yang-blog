package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Comment;
import com.yang.blog.mapper.CommentMapper;
import com.yang.blog.service.ICommentService;
import com.yang.blog.dto.BaseQueryParam;
import com.yang.blog.util.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    /**
     * 分页查询
     *
     * @param queryCondition
     * @return
     */
    @Override
    public Map<String, Object> queryPage(BaseQueryParam queryCondition) {
        List<Map<String, Object>> rows = listMaps(
                new QueryWrapper<Comment>()
                        //.select("id", "username", "nick_name", "create_time")
                        .orderByDesc("id")
                        .last(queryCondition.getPageSql())
        );

        return ResponseData.list(count(), rows);
    }

    /**
     * id删除
     *
     * @param ids
     * @return
     */
    @Override
    public ResponseData<Object> del(List<Long> ids) {
        try {
            removeByIds(ids);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Object> audit(List<Long> ids) {
        //循环
        try {
            ids.forEach(aLong -> {
                Comment comment = new Comment(aLong.intValue(), 1);
                updateById(comment);
            });
            return ResponseData.success();
        } catch (Exception e) {
            //手动回滚，处理try失效问题
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseData.fail(e.getMessage());
        }
    }

    /**
     * 回复
     *
     * @param comment
     * @return
     */
    @Override
    public ResponseData<Object> reply(Comment comment) {
        try {
            updateById(comment);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }
}
