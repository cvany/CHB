package com.chb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.ShopDao;
import com.chb.entity.Shop;
import com.chb.service.ShopService;
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	
	public Shop findShopById(Shop shop) {
		return shopDao.findShopById(shop);
	}

}
