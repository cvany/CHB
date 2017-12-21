package com.chb.dao;

import com.chb.entity.ShopInData;

public interface ShopInDataDao {
	
	//根据商家ID查询商家详情
	ShopInData findByShopId(Integer id);

}
