package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CheckGroup findById(Integer checkGroupId) {
       return checkGroupDao.findById(checkGroupId);
    }

    @Override
    public void delete(Integer checkgroupId) {
        //先清除中间表数据
        removeCheckitemIdsByCheckGroupId(checkgroupId);
        //清除基本数据
        checkGroupDao.removeByCheckgroupId(checkgroupId);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {

        //更新检查组基本信息
        checkGroupDao.edit(checkGroup);
        //删除原有检查组与检查项间联系后重新添加
        removeCheckitemIdsByCheckGroupId(checkGroup.getId());
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    private void removeCheckitemIdsByCheckGroupId(Integer id) {
        checkGroupDao.removeCheckitemIdsByCheckGroupId(id);
    }

    @Override
    public List<Integer> findCheckitemByCheckgroupId(Integer checkGroupId) {
        return checkGroupDao.findCheckitemByCheckgroupId(checkGroupId);
    }

}
