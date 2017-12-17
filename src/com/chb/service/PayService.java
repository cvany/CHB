package com.chb.service;

import com.chb.entity.Order;
import com.chb.entity.ResultMessage;

public interface PayService {
	// 获取支付地址
	ResultMessage getPayUrl(Order order);
}
