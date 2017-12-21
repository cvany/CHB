package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Order;
import com.chb.entity.ResultMessage;
import com.chb.service.OrderService;
import com.chb.service.PayService;
import com.chb.utils.JsonUtil;

@Controller
public class PayController {
	@Autowired
	PayService payService;
	@Autowired
	OrderService orderService;
	
	// 获取支付地址
	@RequestMapping("user/getPayUrl")
	@ResponseBody
	public ResultMessage getPayUrl(String order) throws Exception {
		return payService.getPayUrl(JsonUtil.jsonToObject(order, Order.class));
	}
	
	// 支付成功回调
	@RequestMapping("user/payCallback")
	public String payCallback(String r1_Code,String r2_TrxId,String r6_Order) {
		if(r1_Code.equals("1")) {
			Order order = new Order();
			order.setOrderNo(r6_Order);
			order.setPaySerialsNumber(r2_TrxId);
			orderService.updateOrderPayStatus(order);
			return "redirect:http://172.16.15.193:8080/CHB/pages/front/order/user-order.html";
		}
		return null;
	}
}
