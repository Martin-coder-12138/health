package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/11
 * @Time: 19:25
 * @description
 */

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SetmealDao setmealDao;

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);

        Page<Setmeal> page = setmealDao.pageQuery(queryString);
        PageResult pageResult = new PageResult(page.getTotal(), page.getResult());
        return pageResult;
    }

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        if (checkgroupIds != null && checkgroupIds.length > 0) {

            setCheckgroupIdsBySetmealId(setmeal.getId(), checkgroupIds);
        }

        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());
    }

    /**
     * 将图片名称保存到Redis
     *
     * @param img
     */
    private void savePic2Redis(String img) {
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, img);
    }

    private void setCheckgroupIdsBySetmealId(Integer setmealId, Integer[] checkgroupIds) {
        setmealDao.setCheckgroupIdsBySetmealId(setmealId, checkgroupIds);
    }

    @Override
    public Setmeal queryById(Integer setmealId) {
       return setmealDao.queryById(setmealId);
    }

    @Override
    public List<Integer> findCheckGroupById(Integer setmealId) {
        return setmealDao.findCheckGroupById(setmealId);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        //删除套餐过期检查组数据
        removeCheckGroupsByID(setmeal.getId());
        //更新套餐基本数据
        updateById(setmeal);
        //更新检查组数据
        setCheckgroupIdsBySetmealId(setmeal.getId(), checkgroupIds);
    }


    private void updateById(Setmeal setmeal) {
        setmealDao.updateById(setmeal);
    }

    private void removeCheckGroupsByID(Integer setmealId) {
        setmealDao.removeCheckGroupsByID(setmealId);
    }

    @Override
    public void delete(Integer id) {
        //删除套餐过期检查组数据
        removeCheckGroupsByID(id);
        setmealDao.delete(id);
    }
}
