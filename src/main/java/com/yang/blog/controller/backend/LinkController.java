package com.yang.blog.controller.backend;

import com.yang.blog.entity.Link;
import com.yang.blog.service.ILinkService;
import com.yang.blog.dto.QueryParam;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/17 14:46
 * @description：友情链接控制器
 */
@RequestMapping("admin/content/link")
@Controller
public class LinkController {
    @Autowired
    private ILinkService linkService;

    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @PostMapping("index")
    public Map<String, Object> index(@RequestBody QueryParam queryCondition) {
        return linkService.queryPage(queryCondition);
    }

    /**
     * 添加数据
     *
     * @param link
     * @return
     */
    @ResponseBody
    @PostMapping("add")
    public ResponseData<Object> save(
            @RequestBody
            @Validated Link link,
            BindingResult bindingResult
    ) {
        return linkService.add(link, bindingResult);
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("update")
    public ResponseData<Map<String, Object>> find(@RequestParam(value = "id") Long id) {
        return linkService.find(id);
    }


    /**
     * 修改数据
     *
     * @param link
     * @return
     */
    @ResponseBody
    @PutMapping("update")
    public ResponseData<Object> update(
            @Validated
            @RequestBody Link link,
            BindingResult bindingResult
    ) {
        return linkService.update1(link, bindingResult);
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
        return linkService.del(ids);
    }

    @GetMapping("index")
    public String indexView() {
        return "backend/content/link/index";
    }

    @GetMapping("add")
    public String saveView() {
        return "backend/content/link/add";
    }

    @GetMapping("update")
    public String editView() {
        return "backend/content/link/edit";
    }
}
