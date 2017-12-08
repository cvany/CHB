package com.chb.dao;

import com.chb.entity.Goods;

/**
 * 菜品dao接口类
 * @author cqs
 *
 */
public interface GoodsDao {
	// 通过菜品名查询菜品
	Goods findByGoodsName(String goodsName);
	//查询所有菜品
	Goods findByShopId(int shopId);
}
