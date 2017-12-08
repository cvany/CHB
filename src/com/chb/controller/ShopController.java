package com.chb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.service.ShopService;
import com.chb.utils.JsonUtil;

/**
 * 商店控制类
 * 
 * @author cqs
 *
 */
@Controller
public class ShopController {
	@Autowired
	private ShopService shopService;

	// 管理员登录接口
	@RequestMapping("shop/insertShop")
	@ResponseBody
	public void insertShop(Shop shop){
		shopService.insertShop(shop);
	}

	
}