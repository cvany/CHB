package com.chb.service;

import com.chb.entity.Order;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;

/**
 * 订单服务接口类
 * @author shilim
 *
 */
public interface OrderService {
	// 分类分页查询订单列表
	ResultMessage getOrderListByPage(Page page,Order order);
}
