package com.chb.service.impl;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.chb.entity.Order;
import com.chb.entity.Page;
import com.chb.service.OrderService;
import com.chb.utils.JsonUtil;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-test.xml")
public class OrderServiceImplTest {
	@Resource
	private OrderService orderService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetOrderListByPage() throws Exception {
		Page page = new Page(1, 1, "desc", null, false);
		Order order = new Order();
		order.setStatus(1);
//		System.out.println(JsonUtil.objectToJson(orderService.getOrderListByPage(page, order)));
	}

}
