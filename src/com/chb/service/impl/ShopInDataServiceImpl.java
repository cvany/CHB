package com.chb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.ShopInDataDao;
import com.chb.entity.ShopInData;
import com.chb.service.ShopInDataService;

@Service
public class ShopInDataServiceImpl implements ShopInDataService {
	@Autowired
	private ShopInDataDao shopInDataDao;

	@Override
	public void insertShopInData(ShopInData shopInData){
		shopInDataDao.insertShopInData(shopInData);
	}

}
