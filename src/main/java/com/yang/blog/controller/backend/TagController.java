package com.yang.blog.controller.backend;

import com.yang.blog.service.ITagService;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/17 17:01
 * @description：
 */
@Controller
@RequestMapping("admin/content/tag")
public class TagController {
    //只能查看，和删除

    @Autowired
    private ITagService tagService;

    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @PostMapping("index")
    public Map<String, Object> index(@RequestBody QueryCondition queryCondition) {
        return tagService.queryPage(queryCondition);
    }

    /**
     * id 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("delete")
    public ResponseData<Object> delete(@RequestBody List<Long> ids) {
        return tagService.del(ids);
    }


    @GetMapping("index")
    public String indexView() {
        return "backend/content/tag/index";
    }
}
