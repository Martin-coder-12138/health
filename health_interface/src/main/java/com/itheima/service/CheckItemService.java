package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/6
 * @Time: 19:23
 * @description 检查项服务接口
 */
public interface CheckItemService {

    void add(CheckItem checkItem);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void delete(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);
}
