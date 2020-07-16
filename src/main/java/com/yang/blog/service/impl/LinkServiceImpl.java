package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Link;
import com.yang.blog.mapper.LinkMapper;
import com.yang.blog.service.ILinkService;
import com.yang.blog.dto.BaseQueryParam;
import com.yang.blog.util.ResponseData;
import com.yang.blog.util.VerificationJudgementUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/17 14:47
 * @description：
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

    @Override
    public Map<String, Object> queryPage(BaseQueryParam queryCondition) {
        List<Map<String, Object>> rows = listMaps(
                new QueryWrapper<Link>()
                        .select(
                                "id",
                                "type",
                                "name",
                                "url",
                                "description",
                                "sort",
                                "create_time"
                        )
                        .orderByDesc("sort")
                        .orderByDesc("id")
                        .last(queryCondition.getPageSql())
        );
        return ResponseData.list(count(), rows);
    }

    @Override
    public ResponseData<Object> add(Link link, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgementUtils.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        try {
            save(link);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

    @Override
    public ResponseData<Map<String, Object>> find(Long id) {
        Map<String, Object> map = getMap(
                new QueryWrapper<Link>().
                        eq("id", id).
                        select(
                                "id",
                                "type",
                                "name",
                                "url",
                                "description",
                                "sort"
                        )
        );
        return ResponseData.success(map);
    }

    @Override
    public ResponseData<Object> update1(Link link, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgementUtils.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }

        try {
            updateById(link);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail("数据修改失败！");
        }
    }

    @Override
    public ResponseData<Object> del(List<Long> ids) {
        try {
            removeByIds(ids);
            return ResponseData.success("数据删除成功！");
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

}
