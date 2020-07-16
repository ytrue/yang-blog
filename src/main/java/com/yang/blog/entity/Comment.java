package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@TableName("blog_comment")
@Data
public class Comment extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    @NotNull(message = "所属文章不得为空！")
    @TableField("article_id")
    private Integer articleId;

    /**
     * 评论者名称
     */
    @NotBlank(message = "名称不得为空！")
    @TableField("commentator")
    private String commentator;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不得为空！")
    @Email(message = "邮箱格式不正确！")
    @TableField("email")
    private String email;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不得为空！")
    @TableField("comment_body")
    private String commentBody;

    /**
     * 评论时的ip地址
     */
    @NotBlank(message = "评论ip地址不得为空！")
    @TableField("commentator_ip")
    private String commentatorIp;

    /**
     * 回复内容
     */
    @NotBlank(message = "回复内容不得为空！")
    @TableField("reply_body")
    private String replyBody;

    /**
     * 回复时间
     */
    @TableField(value = "reply_create_time")
    private Date replyCreateTime;

    /**
     * 是否审核通过
     */
    @TableField(value = "reply_create_time")
    @NotNull(message = "审核状态不得为空！")
    private Integer commentStatus;

    public Comment(Integer id, Integer commentStatus) {
        this.id = id;
        this.commentStatus = commentStatus;
    }
}
