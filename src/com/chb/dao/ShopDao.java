package com.chb.dao;

import com.chb.entity.Shop;

/**
 * 商家dao接口类
 * @author cqs
 *
 */
public interface ShopDao {
	
	//存入入驻信息
	void insertShop(Shop shop);
	/**
	 * 根据商店id查找商店信息
	 * @param shop
	 * @return
	 */
	Shop findShopById(Shop shop);
	
	Shop findShopByName(String shopName);
	
	
	void updateShopById(Shop shop);
	
	void updateDispatching(Shop shop);
	
	void updateAddress(Shop shops);

	
}
