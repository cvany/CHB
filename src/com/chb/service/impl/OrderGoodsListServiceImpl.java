package com.chb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.OrderGoodsListDao;
import com.chb.entity.Order;
import com.chb.entity.ResultMessage;
import com.chb.service.OrderGoodsListService;

/**
 * 订单商品列表服务实现类
 * 
 * @author shilim
 *
 */
@Service
public class OrderGoodsListServiceImpl implements OrderGoodsListService {

	@Autowired
	OrderGoodsListDao orderGoodsListDao;

	@Override
	public ResultMessage getGoodsListByOrderId(Order order) {
		return new ResultMessage(true, ResultCode.SUCCESS, "获取成功",
				orderGoodsListDao.selectOrderGoodsListByOrderId(order.getId()));
	}

}
