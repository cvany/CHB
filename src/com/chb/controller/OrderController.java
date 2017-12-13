package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Order;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.service.OrderService;
import com.chb.utils.JsonUtil;

/**
 * 订单管理控制器
 * 
 * @author shilim
 *
 */
@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	// 分类分页获取订单列表
	@RequestMapping("business/getOrderListByPage")
	@ResponseBody
	public ResultMessage getOrderListByPage(String page,String order) throws Exception {
		return orderService.getOrderListByPage(JsonUtil.jsonToObject(page, Page.class), 
				JsonUtil.jsonToObject(order, Order.class));
	}
	
	// 下单(创建订单)
	@RequestMapping("user/newOrder")
	@ResponseBody
	public ResultMessage newOrder(String order) {
		return new ResultMessage();
	}
	
	// 
	@RequestMapping("user/updatePayStatus")
	@ResponseBody
	public ResultMessage updatePayStatus(String order) {
		return new ResultMessage();
	}
}
