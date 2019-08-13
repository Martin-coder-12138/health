package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/11
 * @Time: 19:35
 * @description
 */
public interface SetmealDao {
    public Page<Setmeal> pageQuery(@Param("queryString") String queryString);

    void add(Setmeal setmeal);

    void setCheckgroupIdsBySetmealId(@Param("setmealId") Integer setmealId,@Param("checkgroupIds") Integer[] checkgroupIds);

    Setmeal queryById(@Param("setmealId") Integer setmealId);

    List<Integer> findCheckGroupById(@Param("setmealId") Integer setmealId);

    void removeCheckGroupsByID(Integer setmealId);


    void updateById(Setmeal setmeal);

    void delete(Integer id);
}
