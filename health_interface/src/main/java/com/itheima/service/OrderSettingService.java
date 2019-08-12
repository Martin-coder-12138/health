package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/12
 * @Time: 23:53
 * @description
 */

public interface OrderSettingService {
    /**
     * 批量导入预约数据
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList);

    /**
     * 获取预约设置数据
     * @param date 参数格式为"2001-01"
     * @return
     */
    List<Map> getOrderSettingByMonth(String date);

    void editNumberBayDate(OrderSetting orderSetting);
}
