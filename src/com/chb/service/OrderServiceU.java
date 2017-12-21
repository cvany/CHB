package com.chb.service;

import java.util.List;

import com.chb.entity.Order;
import com.chb.entity.OrderUVo;

public interface OrderServiceU {
	
	  List<Order> getAllOrderById(int id);
	  

	  List<Order> getOrderStatusById(OrderUVo orderUVo);
}
