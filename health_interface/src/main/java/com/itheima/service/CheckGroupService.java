package com.itheima.service;

import com.itheima.pojo.CheckGroup;

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

}
