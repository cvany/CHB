package com.chb.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.chb.entity.Order;
import com.chb.entity.OrderGoodsList;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;

/**
 * 订单服务接口类
 * 
 * @author shilim
 *
 */
public interface OrderService {
	// 商家分类分页查询订单列表
	ResultMessage getOrderListByPage(Page page, Order order, HttpSession session);

	// 添加订单
	ResultMessage newOrder(Order order, List<OrderGoodsList> orderGoodsList, HttpSession session);

	// 商家接单
	ResultMessage takeOrder(Order order);

	// 开始派送
	ResultMessage sendGoods(Order order);

	// 修改支付状态
	Integer updateOrderPayStatus(Order order);

	// 催单
	ResultMessage remind(Order order);

	// 用户分类分页查询订单列表
	ResultMessage getOrderListByPageFromUser(Page page, Order order, HttpSession session);

	// 用户确认收货
	ResultMessage confirmReceipt(Order order);
}
