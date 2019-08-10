package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.CheckGroupDao;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/10
 * @Time: 16:50
 * @description
 */

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        /*添加检查组基本信息*/
        checkGroupDao.addCheckGroup(checkGroup);
        /*设置检查组及检查项关联信息*/
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    private void setCheckGroupAndCheckItem(Integer id, Integer[] checkitemIds) {
        checkGroupDao.setCheckGroupAndCheckItem(id, checkitemIds);
    }
}
