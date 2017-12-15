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

	// 分类分页查询订单列表
	@Override
	public ResultMessage getOrderListByPage(Page page, Order order) {
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
//		order.setUserId(((User)session.getAttribute("user")).getId());
		order.setUserId(1);
		orderDao.addOrder(order);
		order.setOrderNo(TimeUtil.getCurrentTimeString("yyyyMMddHHmmss")+order.getShopId()+order.getId());
		orderDao.updateOrderNum(order);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", order);
		map.put("orderGoodsList", orderGoodsList);
		orderGoodsListDao.addOrderGoodsList(map);
		System.out.println(order);
		return new ResultMessage(true, ResultCode.SUCCESS, "下单成功", null);
	}

}
