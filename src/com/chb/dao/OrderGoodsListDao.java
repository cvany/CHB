package com.chb.dao;

import java.util.List;

import com.chb.entity.OrderGoodsList;

public interface OrderGoodsListDao {
	// 根据订单id获取商品列表
	List<OrderGoodsList> selectOrderGoodsListByOrderId(Integer orderId);
}
