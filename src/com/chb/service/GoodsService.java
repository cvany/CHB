package com.chb.service;

import java.util.List;

import com.chb.entity.Goods;
import com.chb.entity.Shop;

public interface GoodsService {
	
	/**
	 * 根据商店id查找所有商品信息
	 * @param shop
	 * @return
	 */
	List<Goods> findAllGoodsByShopId(Shop shop);
	
	/**
	 * 根据关键字模糊查询商品
	 * @param goods
	 * @return
	 */
	List<Goods>	findGoodsByKeywords(Goods goods);

}
