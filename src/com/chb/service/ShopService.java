package com.chb.service;

import org.springframework.stereotype.Service;

import com.chb.entity.Shop;


public interface ShopService {
	
	/**
	 * 根据商店id查找商店信息
	 * @param shop
	 * @return
	 */
	Shop findShopById(Shop shop);

}
