package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/10
 * @Time: 15:21
 * @description
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    private static final Logger LOGGER = Logger.getLogger(CheckGroupController.class);
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
            return Result.success(MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("添加检查组失败");
            return Result.fail(MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping("/findById")
    public Result findById(Integer checkGroupId) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(checkGroupId);
            return Result.success(MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e) {
            LOGGER.error("查询检查组信息失败");
            return Result.fail(MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }


    @RequestMapping("/findCheckitemByCheckgroupId")
    public Result findCheckitemByCheckgroupId(Integer checkGroupId) {
        try {
            List<Integer> checkitems = checkGroupService.findCheckitemByCheckgroupId(checkGroupId);
            return Result.success(MessageConstant.QUERY_CHECKITEM_SUCCESS, checkitems);
        } catch (Exception e) {
            LOGGER.error("查询检查项shibai");
            return Result.fail(MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.edit(checkGroup, checkitemIds);
            return Result.success(MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("编辑检查组失败");
            return Result.fail(MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer checkgroupId) {
        try {
            checkGroupService.delete(checkgroupId);
            return Result.success(MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("删除检查组失败");
            return Result.fail(MessageConstant.DELETE_CHECKGROUP_FAIL);

        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckGroup> checkGroups = checkGroupService.findAll();
            return Result.success(MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroups);
        } catch (Exception e) {
            LOGGER.error("findall查询检查组失败" + e);
            return Result.fail(MessageConstant.QUERY_CHECKGROUP_FAIL);

        }

    }



}
