package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Shop;
import com.chb.service.ShopService;

/**
 * 商店控制类
 * @author van元
 * 2017年12月11日
 *
 */
@Controller
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	/**
	 * 根据商店id查找商店信息
	 * @param shop
	 * @return
	 */
	@RequestMapping("findShopInfoById")
	@ResponseBody
	public Shop findShopById(Shop shop){
		return shopService.findShopById(shop);
	}
}
