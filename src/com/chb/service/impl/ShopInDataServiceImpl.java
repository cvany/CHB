package com.chb.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.ShopInDataDao;
import com.chb.entity.ResultMessage;
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
