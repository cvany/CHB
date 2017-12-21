package com.chb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chb.constant.ResultCode;
import com.chb.dao.OrderDao;
import com.chb.dao.OrderGoodsListDao;
import com.chb.entity.Order;
import com.chb.entity.OrderGoodsList;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;
import com.chb.service.OrderService;
import com.chb.utils.TimeUtil;

/**
 * 订单服务实现类
 * 
 * @author shilim
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;
	@Autowired
	OrderGoodsListDao orderGoodsListDao;

	// 商家分类分页查询订单列表
	@Override
	public ResultMessage getOrderListByPage(Page page, Order order, HttpSession session) {
		if (session.getAttribute("businessmanId") != null) {
			order.setShopId(Integer.parseInt(session.getAttribute("businessmanId").toString()));
		} else {
			return new ResultMessage(false, ResultCode.NO_LOGIN, "未登录", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("order", order);
		page.coutStartColum();
		long total = orderDao.selectCountByPage(map);
		List<Order> orderList = orderDao.selectOrderListByPage(map);
		PageInfo<Order> pageInfo = new PageInfo<Order>(page, total, orderList);
		return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", pageInfo);
	}

	// 下单(添加订单)
	@Override
	public ResultMessage newOrder(Order order, List<OrderGoodsList> orderGoodsList, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			order.setUserId(user.getId());
			// order.setUserId(1);
			orderDao.addOrder(order);
			order.setOrderNo(TimeUtil.getCurrentTimeString("yyyyMMddHHmmss") + order.getShopId() + order.getId());
			orderDao.updateOrderNum(order);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("order", order);
			map.put("orderGoodsList", orderGoodsList);
			orderGoodsListDao.addOrderGoodsList(map);
			System.out.println(order);
			return new ResultMessage(true, ResultCode.SUCCESS, "下单成功", order);
		}
		return new ResultMessage(false, ResultCode.NO_LOGIN, "未登录", null);
	}

	// 商家接单
	@Override
	public ResultMessage takeOrder(Order order) {
		order.setStatus(2);
		orderDao.updateOrderStatus(order);
		return new ResultMessage(true, ResultCode.SUCCESS, "接单成功", null);
	}

	// 开始配送
	@Override
	public ResultMessage sendGoods(Order order) {
		order.setStatus(3);
		orderDao.updateOrderStatus(order);
		return new ResultMessage(true, ResultCode.SUCCESS, "开始配送成功", null);
	}

	@Override
	public Integer updateOrderPayStatus(Order order) {
		return orderDao.updateOrderPayStatus(order);
	}
	
	// 催单
	@Override
	public ResultMessage remind(Order order) {
		orderDao.updateReminderById(order.getId());
		return new ResultMessage(true, ResultCode.SUCCESS, "修改成功", null);
	}

	// 用户分页获取订单列表
	@Override
	public ResultMessage getOrderListByPageFromUser(Page page, Order order, HttpSession session) {
		if (session.getAttribute("user") != null) {
			order.setUserId(((User)session.getAttribute("user")).getId());
		} else {
			return new ResultMessage(false, ResultCode.NO_LOGIN, "未登录", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("order", order);
		page.coutStartColum();
		long total = orderDao.selectCountByPageFromUser(map);
		List<Order> orderList = orderDao.selectOrderListByPageFromUser(map);
		PageInfo<Order> pageInfo = new PageInfo<Order>(page, total, orderList);
		return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", pageInfo);
	}

	@Override
	public ResultMessage confirmReceipt(Order order) {
		order.setStatus(5);
		orderDao.updateOrderStatus(order);
		return new ResultMessage(true, ResultCode.SUCCESS, "确认收货成功", null);
	}


}
