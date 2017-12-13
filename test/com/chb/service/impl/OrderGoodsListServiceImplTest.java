package com.chb.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.chb.entity.Order;
import com.chb.service.OrderGoodsListService;
import com.chb.utils.JsonUtil;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-test.xml")
public class OrderGoodsListServiceImplTest {
	@Resource
	private OrderGoodsListService orderGoodsListService;

	@Test
	public void testGetGoodsListByOrderId() throws Exception {
		Order order = new Order();
		order.setId(1);
		System.out.println(
				JsonUtil.objectToJson(orderGoodsListService.getGoodsListByOrderId(order)));
	}

}
