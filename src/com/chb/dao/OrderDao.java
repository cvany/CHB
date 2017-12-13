package com.chb.dao;

import java.util.List;
import java.util.Map;

import com.chb.entity.Order;

/**
 * 订单管理dao接口
 * 
 * @author shilim
 *
 */
public interface OrderDao {
	// 分类分页查询订单列表总页数
	Long selectCountByPage(Map<String, Object> map);

	// 分类分页查询订单列表
	List<Order> selectOrderListByPage(Map<String, Object> map);
	
	// 增加订单
	Void addOrder(Order order);
	
	// 修改订单编号
	Void updateOrderNum(Order order);
	
	// 修改订单状态
	Void updateOrderStatus(Order order);
	
	// 修改支付状态
	Void updateOrderPayStatus(Order order);
	
}
