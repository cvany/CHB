package com.chb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chb.entity.Order;
import com.chb.entity.OrderUVo;

@Repository("OrderDaoU")
public interface OrderDaoU {

	List<Order> getAllOrderById(int id);
	
	List<Order> getOrderStatusById(OrderUVo orderUVo);


}
