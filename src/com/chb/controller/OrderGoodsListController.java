package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Order;
import com.chb.entity.ResultMessage;
import com.chb.service.OrderGoodsListService;
import com.chb.utils.JsonUtil;

@Controller
public class OrderGoodsListController {
	@Autowired
	OrderGoodsListService orderGoodsListService;
	
	@RequestMapping("business/getOrderGoodsListByOrderId")
	@ResponseBody
	public ResultMessage getOrderGoodsListByOrderId(String order) throws Exception {
		return orderGoodsListService.getGoodsListByOrderId(JsonUtil.jsonToObject(order, Order.class));
	}
}
