package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;

public interface ShopService {
	
	//存入商店信息
	void insertShop(Shop shop);
	/**
	 * 根据商店id查找商店信息
	 * @param shop
	 * @return
	 */
	ResultMessage findNotice(HttpSession httpSession);
	/**
	 * 根据商店id查找商店信息
	 * @param shop
	 * @return
	 */
	Shop findShopById(Shop shop);
	
	Shop findShopByName(String shopName);
	//根据id更新商家信息
	void updateShopById(Shop shop);
	
	void updateDispatching(Shop shops);
	
	void updateAddress(Shop shops);
	

}
