package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.dto.QueryParam;
import com.yang.blog.entity.Tag;
import com.yang.blog.mapper.TagMapper;
import com.yang.blog.service.ITagService;
import com.yang.blog.util.ResponseData;
import com.yang.blog.util.SearchUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    /**
     * 分页查询
     *
     * @param queryCondition
     * @return
     */
    @Override
    public Map<String, Object> queryPage(QueryParam queryCondition) {
        QueryWrapper<Tag> queryWrapper = SearchUtil.parseWhereSql(new QueryWrapper<>(), queryCondition.getCondition());
        int count = count(queryWrapper);
        List<Map<String, Object>> rows = listMaps(
                queryWrapper
                        .select("id", "name", "number")
                        .orderByDesc("id")
                        .last(queryCondition.getPageSql())
        );

        return ResponseData.list(count, rows);
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
}
