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

}
