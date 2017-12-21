package com.chb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.OrderDaoU;
import com.chb.entity.Order;
import com.chb.entity.OrderUVo;
import com.chb.service.OrderServiceU;

@Service
public class OrderServiceImplU implements OrderServiceU{
	@Autowired
	private OrderDaoU OrderDaoU;
	

	@Override
	public List<Order> getAllOrderById(int id) {
		
		return OrderDaoU.getAllOrderById(id);
	}


	@Override
	public List<Order> getOrderStatusById(OrderUVo orderUVo) {
		// TODO Auto-generated method stub
		return OrderDaoU.getOrderStatusById(orderUVo);
	}

}
