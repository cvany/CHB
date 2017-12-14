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

	//
	@RequestMapping("insertShop")
	@ResponseBody
	public void insertShop(String shop) throws Exception{
		Shop shops = new Shop();
		shops=JsonUtil.jsonToObject(shop, Shop.class);
		shopService.insertShop(shops);
	}
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
