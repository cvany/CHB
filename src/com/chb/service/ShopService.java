package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.Shop;


public interface ShopService {
	
	ResultMessage findNotice(HttpSession httpSession);
	
	Shop findShopByName(String shopName);
	//根据id更新商家信息
	void updateShopById(Shop shop);
	
	void updateDispatching(Shop shops);
	
	void updateAddress(Shop shops);

}
