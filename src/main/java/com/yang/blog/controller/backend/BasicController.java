package com.yang.blog.controller.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.blog.entity.Permission;
import com.yang.blog.service.IPermissionService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：yangyi
 * @date：2020/5/23 14:13
 * @description：
 */
public class BasicController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 侧栏查询放行
     */
    protected List<String> loadUrlList = Lists.newArrayList("index", "save", "edit");

    /**
     * 加载公共数据
     *
     * @param model
     */
    @ModelAttribute
    public void loadCommon(HttpServletRequest request, Model model) {

        System.out.println(loadUrlList);
        if (urlJudge(request.getRequestURI())) {
            List<Permission> permissionList = permissionService.list(
                    new QueryWrapper<>()
            );
            List<Permission> menu = getChildManyGroup(permissionList, 0);
            model.addAttribute("menu", menu);
        }
    }

    /**
     * 根据id返回所有子集分类,(多维List集合,List中含有子集List)
     * <p>
     * 1
     * 11
     * 111
     * 2
     * 22
     * 222
     *
     * @param list
     * @param pid
     * @return
     */
    private List<Permission> getChildManyGroup(List<Permission> list, int pid) {

        List<Permission> arr = new ArrayList<>();
        for (Permission permission : list) {
            if (pid == permission.getPid()) {
                permission.setChildrenList(getChildManyGroup(list, permission.getId()));
                arr.add(permission);
            }
        }
        return arr;
    }


    private Boolean urlJudge(String uri) {
        String[] uriArr = uri.split("\\/");
        int urlArrLength = uriArr.length;
        String lastUriString = uriArr[urlArrLength - 1];
        return loadUrlList.contains(lastUriString);
    }
}
