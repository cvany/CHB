package com.chb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.ResultMessage;
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

	// 管理员登录接口
	@RequestMapping("shopInData/insertShopInData")
	@ResponseBody
	public void insertShopInData(ShopInData shopInData){
		shopInDataService.insertShopInData(shopInData);
	}

	
}
