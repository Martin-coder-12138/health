package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @version V1.0
 * @author: ZhangZheng
 * @Date: 2019/8/12
 * @Time: 23:50
 * @description
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    private static final Logger LOGGER = Logger.getLogger(OrderSettingController.class);

    @RequestMapping("/update")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            //读取Excel文件
            List<String[]> list = POIUtils.readExcel(excelFile);
            if (list != null && list.size() > 0) {
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] strings : list) {
                    OrderSetting orderSetting = new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
                    orderSettingList.add(orderSetting);
                }
                orderSettingService.add(orderSettingList);
            }

            return Result.success(MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            LOGGER.error("批量导入预约设置数据失败" + e);
            return Result.fail(MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(@RequestParam("date") String date) {
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            //获取预约设置数据成功
            return Result.success(MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("获取预约设置数据失败" + e);
            return Result.fail(MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberBayDate")
    public Result editNumberBayDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberBayDate(orderSetting);
            return Result.success(MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("修改可预约人数失败");
            return Result.fail(MessageConstant.ORDERSETTING_FAIL);
        }
    }


}
