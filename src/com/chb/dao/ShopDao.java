package com.chb.dao;
/**
 * 商家Dao层接口
 * @author van元
 * 2017年12月11日
 *
 */

import java.util.List;

import com.chb.entity.Message;
import com.chb.entity.Page;
import com.chb.entity.Shop;

public interface ShopDao {
	
	/**
	 * 根据商店id查找商店信息
	 * @param shop
	 * @return
	 */
	Shop findShopById(Shop shop);
	//查询审核总页数
	long selectCount(Page page);
	//查询待审核商家
	List<Shop> getBusinessInDataListByPage(Page page);
	//审核通过
	Integer updateShopById(Integer id);
	//审核不通过
	Integer deleteShopById(Integer id);
	//查询所有商家
	List<Shop> getBusinessListByPage(Page page);
	//查询所有商家页数
	long selectAllCount(Page page);
	//更新商家信息
	Shop updateShop(Shop shop);
	//返回信息给商家
	Integer insertMessage(Message message);
	//查看商家数据 分析
	long selectCountByCredibility(long i);
	//根据商家类别统计数量
	long selectCountByShopClassifyId(long i);
	
	Shop findShopByName(String shopName);
	
	void updateShopById(Shop shop);
	
	void updateDispatching(Shop shop);
	
	void updateAddress(Shop shops);

}
