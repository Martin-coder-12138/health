package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/11
 * @Time: 19:24
 * @description
 */
public interface SetmealService {
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void add(Setmeal setmeal, Integer[] checkgroupIds);
}
