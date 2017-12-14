package com.chb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.service.ShopInDataService;
import com.chb.utils.JsonUtil;

/**
 *商家入驻信息控制类
 * 
 * @author cqs
 *
 */
@Controller
public class ShopInDataController {
	@Autowired
	private ShopInDataService shopInDataService;

	//
	@RequestMapping("insertShopInData")
	@ResponseBody
	public void insertShopInData(String shopInData) throws Exception{
		ShopInData shopInDatas = new ShopInData();
		shopInDatas=JsonUtil.jsonToObject(shopInData, ShopInData.class);
		shopInDataService.insertShopInData(shopInDatas);
	}
	
}
