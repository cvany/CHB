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
	Shop findShopById(Shop shop);
	
	Shop findShopByName(String shopName);

}
