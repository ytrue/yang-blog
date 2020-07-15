package com.yang.blog.controller.frontend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.blog.entity.Link;
import com.yang.blog.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller("FrontendLinkController")
public class LinkController {

    @Autowired
    private ILinkService linkService;

    @GetMapping("link")
    public String index(Map<String, Object> map) {
        List<Link> links = linkService.list(
                new QueryWrapper<Link>()
                        .orderByAsc("type")
                        .orderByDesc("id")
        );

        map.put("links", links);
        System.out.println(links);
        return "frontend/link";
    }
}
