package com.chb.dao;

import java.util.List;

import com.chb.entity.Page;
import com.chb.entity.Shop;

public interface ShopDao {
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

}
