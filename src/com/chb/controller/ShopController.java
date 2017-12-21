package com.chb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Businessman;
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
	public int insertShop(String shop) throws Exception{
		Shop shops = new Shop();
		shops=JsonUtil.jsonToObject(shop, Shop.class);
		shopService.insertShop(shops);
		Shop bp = shopService.findShopByName(shops.getShopName());
		int id=0;
		if(bp!=null)
		id=bp.getId();
		return id;
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
	
	@RequestMapping("findNotice")
	@ResponseBody
	public ResultMessage findNotice(HttpSession httpSession){
		return shopService.findNotice(httpSession);
	}
	
	
	@RequestMapping("getDispatchByBusinessmanId")
	@ResponseBody
	public ResultMessage getDispatchByBusinessmanId(HttpSession httpSession){
		return shopService.findNotice(httpSession);
	}
	
	
	@RequestMapping("updateDispatching")
	@ResponseBody
	public void updateDispatching(String shop,HttpSession httpSession) throws Exception{
		Shop shops = new Shop();
		shops=JsonUtil.jsonToObject(shop, Shop.class);
		String businessmanId = httpSession.getAttribute("businessmanId").toString();
		if(businessmanId!=null){
			shops.setBusinessmanId(Integer.parseInt(businessmanId));
		shopService.updateDispatching(shops);
	}
	}
	
	@RequestMapping("updateShopById")
	@ResponseBody
	public void updateShopById(String shop,HttpSession httpSession) throws Exception{
		Shop shops = new Shop();
		shops=JsonUtil.jsonToObject(shop, Shop.class);
		String businessmanId = httpSession.getAttribute("businessmanId").toString();
		if(businessmanId!=null){
			shops.setBusinessmanId(Integer.parseInt(businessmanId));
		shopService.updateShopById(shops);
	}
	}
	
	@RequestMapping("updateAddress")
	@ResponseBody
	public void updateAddress(String shop,HttpSession httpSession) throws Exception{
		Shop shops = new Shop();
		shops=JsonUtil.jsonToObject(shop, Shop.class);
		String businessmanId = httpSession.getAttribute("businessmanId").toString();
		if(businessmanId!=null){
			shops.setBusinessmanId(Integer.parseInt(businessmanId));
		shopService.updateAddress(shops);
	}
	}
	
}
