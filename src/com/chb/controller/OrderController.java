package com.chb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chb.entity.Order;
import com.chb.entity.OrderGoodsList;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.service.OrderService;
import com.chb.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;

/**
 * 订单管理控制器
 * 
 * @author shilim
 *
 */
@RestController
public class OrderController {
	@Autowired
	OrderService orderService;

	// 分类分页获取订单列表
	@RequestMapping("business/getOrderListByPage")
	public ResultMessage getOrderListByPage(String page,String order) throws Exception {
		return orderService.getOrderListByPage(JsonUtil.jsonToObject(page, Page.class), 
				JsonUtil.jsonToObject(order, Order.class));
	}
	
	// 商家接单
	@RequestMapping("business/takeOrder")
	public ResultMessage takeOrder(String order) throws Exception {
		return orderService.takeOrder(JsonUtil.jsonToObject(order, Order.class));
	}
	
	// 开始派送
	@RequestMapping("business/sendGoods")
	public ResultMessage sendGoods(String order) throws Exception {
		return orderService.sendGoods(JsonUtil.jsonToObject(order, Order.class));
	}
	
	// 下单(创建订单)
	@RequestMapping("user/newOrder")
	public ResultMessage newOrder(String order,String orderGoodsList,HttpSession session) throws Exception {
		return orderService.newOrder(JsonUtil.jsonToObject(order, Order.class), 
				JsonUtil.jsonToObject(orderGoodsList, new TypeToken<List<OrderGoodsList>>(){}.getType()), session);
	}
	
	// 修改支付状态
	@RequestMapping("user/updatePayStatus")
	public ResultMessage updatePayStatus(String order) {
		return new ResultMessage();
	}
	
}
