package com.chb.service;

import java.util.List;

import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.Shop;
import com.chb.entity.ShopClassify;

public interface ShopClassifyService {
	
	/**
	 * 查找所有商店分类
	 * @return
	 */
	List<ShopClassify> findAllShopClassify();
	
	/**
	 * 分页查找商店
	 * @param page
	 * @return
	 */
	List<Shop> findShopByPage(Page page);
	
	/**
	 * 根据商店id查找最新上传的3件商品
	 * @param shop
	 * @return
	 */
	List<Goods> findGoodsByShopId(Shop shop);
	
	/**
	 * 根据商店名称模糊查询商店
	 * @param shop
	 * @return
	 */
	List<Shop> findShopByKeyWords(Shop shop);

}
