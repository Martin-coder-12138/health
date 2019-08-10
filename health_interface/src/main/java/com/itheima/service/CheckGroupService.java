package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/10
 * @Time: 16:48
 * @description
 */
public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findById(Integer checkGroupId);

    List<Integer> findCheckitemByCheckgroupId(Integer checkGroupId);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    void delete(Integer checkgroupId);
}
