package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.Goods;
import com.chb.entity.ResultMessage;

public interface GoodsService {
	// 根据菜品名称菜品查询
	Goods findByGoodsName(String goodsName);
	//根据商店id查询所有菜品
	Goods findByShopId(int shopId);
}
