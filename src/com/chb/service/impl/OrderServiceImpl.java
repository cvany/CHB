package com.chb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.OrderDao;
import com.chb.entity.Order;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.service.OrderService;

/**
 * 订单服务实现类
 * 
 * @author shilim
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;

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

}
