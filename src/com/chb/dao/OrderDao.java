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
	// 商家分类分页查询订单列表总页数
	Long selectCountByPage(Map<String, Object> map);

	// 商家分类分页查询订单列表
	List<Order> selectOrderListByPage(Map<String, Object> map);
	
	// 增加订单
	Integer addOrder(Order order);
	
	// 修改订单编号
	Integer updateOrderNum(Order order);
	
	// 修改订单状态
	Integer updateOrderStatus(Order order);
	
	// 修改支付状态
	Integer updateOrderPayStatus(Order order);
	
	long selectCountByPrice(long i);
	//根据支付方式查看订单数量
	long selectCountByPayMode(long i);
	
	// 通过id查询订单
	Order selectOrderById(Integer id);
	
	// 通过id修改催单状态
	Integer updateReminderById(Integer id);

	// 用户分类分页查询订单列表总页数
	Long selectCountByPageFromUser(Map<String, Object> map);

	// 用户分类分页查询订单列表
	List<Order> selectOrderListByPageFromUser(Map<String, Object> map);
}
