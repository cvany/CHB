package com.chb.service;

import com.chb.entity.Order;
import com.chb.entity.ResultMessage;

public interface OrderGoodsListService {
	// 通过订单id获取订单商品列表
	ResultMessage getGoodsListByOrderId(Order order);
}
