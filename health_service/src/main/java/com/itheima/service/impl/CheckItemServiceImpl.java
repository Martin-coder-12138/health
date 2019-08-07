package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.pojo.CheckItem;
import com.itheima.entity.PageResult;
import com.itheima.service.CheckItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/6
 * @Time: 19:32
 * @description 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 编辑
     * @param checkItem
     */
    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    /**
     * 通过检查项目id查询项目项目详情
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    /**
     * 删除检查项
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //查询当前检查检查项是否和检查组关联
        long count = checkItemDao.findCountByCheckItemId(id);
        if (count > 0) {
            //当前检查项被引用,不能删除
            throw new RuntimeException("当前检查项被引用,不能删除");
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 新增
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
}
