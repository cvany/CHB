package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.ResultMessage;
import com.chb.entity.ShopInData;

public interface ShopInDataService {
	
	//存入商店入驻信息
	void insertShopInData(ShopInData shopInData);
}
