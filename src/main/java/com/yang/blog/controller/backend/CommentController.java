package com.yang.blog.controller.backend;

import com.yang.blog.entity.Comment;
import com.yang.blog.service.ICommentService;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("admin/auth/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @PostMapping("index")
    public Map<String, Object> index(@RequestBody QueryCondition queryCondition) {
        return commentService.queryPage(queryCondition);
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
        return commentService.del(ids);
    }


    /**
     * 回复
     * @param comment
     * @return
     */
    @ResponseBody
    @PostMapping("reply")
    public ResponseData<Object> reply(@RequestBody Comment comment) {
        return commentService.reply(comment);
    }

    /**
     * 审核
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @PostMapping("audit")
    public ResponseData<Object> audit(@RequestBody List<Long> ids) {
        return commentService.audit(ids);
    }

    @GetMapping("index")
    public String indexView() {
        return "backend/auth/admin/index";
    }
}
