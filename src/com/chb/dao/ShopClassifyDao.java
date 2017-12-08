package com.chb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.Shop;
import com.chb.entity.ShopClassify;

/**
 * 商店分类Dao
 * @author 崔文元
 * 2017年12月7日
 *
 */
@Repository("shopClassifyDao")
public interface ShopClassifyDao {
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

}
