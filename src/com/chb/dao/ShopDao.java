package com.chb.dao;
/**
 * 商家Dao层接口
 * @author van元
 * 2017年12月11日
 *
 */

import com.chb.entity.Shop;

public interface ShopDao {
	
	/**
	 * 根据商店id查找商店信息
	 * @param shop
	 * @return
	 */
	Shop findShopById(Shop shop);
}
