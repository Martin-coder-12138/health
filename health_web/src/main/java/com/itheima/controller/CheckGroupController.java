package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
