package com.chb.dao;


import java.util.List;
import java.util.Map;

import com.chb.entity.Goods;
import com.chb.entity.Shop;

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
	// 通过id查询Goods
		Goods selectByPrimaryKey(Integer id);
		// 分页查询菜品总条数
		Long selectCountByPage(Map<String, Object> map);
		// 分页查询菜品
		List<Goods> selectGoodsByPage(Map<String, Object> map);
		// 添加菜品
		void addGoods(Goods goods);
		// 修改菜品
		void updateGoods(Goods goods);
		// 删除菜品
		void deleteGoods(List<Goods> goods);
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
