package com.zhc.dubbo.consumser.server.controller;

import com.zhc.dubbo.producer.api.response.BaseResponse;
import com.zhc.dubbo.producer.api.service.IDubboItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/14 7:11 PM
 * @description :
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    IDubboItemService dubboItemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> list() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        resultMap.put("msg", "成功");

        try {
            BaseResponse response = dubboItemService.listItems();
            if (response != null && response.getCode().equals(0)) {
                resultMap.put("data", response.getData());
            }
        } catch (Exception e) {
            LOGGER.error("occurred error while executed list", e);
            resultMap.put("code", "-1");
            resultMap.put("msg", "失败");
        }
        return resultMap;
    }
}
