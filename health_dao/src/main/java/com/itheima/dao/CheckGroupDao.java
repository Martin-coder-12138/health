package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/10
 * @Time: 16:52
 * @description
 */
public interface CheckGroupDao {
    void addCheckGroup(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(@Param("checkgroup_id") Integer id,@Param("checkitem_id") Integer[] checkitemIds);

    Page<CheckGroup> selectByCondition(@Param("queryString")String queryString);

    CheckGroup findById(Integer checkGroupId);

    List<Integer> findCheckitemByCheckgroupId(Integer checkGroupId);


    void removeCheckitemIdsByCheckGroupId(@Param("checkgroup_id") Integer checkgroupId);

    void edit(CheckGroup checkGroup);

    void removeByCheckgroupId(Integer checkgroupId);
}
