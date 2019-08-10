package com.itheima.dao;

import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

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
}
