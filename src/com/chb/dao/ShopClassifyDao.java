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
	
	/**
	 * 根据商店名称模糊查询商店
	 * @param shop
	 * @return
	 */
	List<Shop> findShopByKeyWords(Shop shop);
	
	/**
	 * 根据商品名称模糊查询商店id集合
	 * @param shop 这里用shop作为参数主要是因为前台传递过来的是shopName属性，所以用它来设置shopName属性值
	 * @return
	 */
	List<Shop> findShopIdByGoodsName(Shop shop);
	/**
	 * 根据商店id集合查找商店主要展示信息
	 * @param shop
	 * @return
	 */
	Shop findShopByShopIdList(Shop shop);
	/**
	 * 根据商店分类id查找商店信息
	 * @param sc
	 * @return
	 */
	List<Shop> findShopByShopClassifyId(ShopClassify sc);

}
