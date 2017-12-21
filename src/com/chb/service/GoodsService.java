package com.chb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;

public interface GoodsService {
	// 根据菜品名称菜品查询
	Goods findByGoodsName(String goodsName);

	// 根据商店id查询所有菜品
	Goods findByShopId(int shopId);

	// 通过page获取菜品列表
	ResultMessage getGoodsByPage(Page page, HttpSession session);

	// 通过id获取菜品
	ResultMessage getGoodsById(Goods goods);

	// 添加菜品
	ResultMessage addGoods(Goods goods, HttpSession session, HttpServletRequest request);

	// 修改菜品
	ResultMessage updateGoods(Goods goods, HttpServletRequest request);

	// 删除菜品
	ResultMessage deleteGoods(List<Goods> goods);

	/**
	 * 根据商店id查找所有商品信息
	 * 
	 * @param shop
	 * @return
	 */
	List<Goods> findAllGoodsByShopId(Shop shop);

	/**
	 * 根据关键字模糊查询商品
	 * 
	 * @param goods
	 * @return
	 */
	List<Goods> findGoodsByKeywords(Goods goods);

}
