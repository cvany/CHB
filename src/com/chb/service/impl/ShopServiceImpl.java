package com.chb.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.ShopDao;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	
	public Shop findShopById(Shop shop) {
		return shopDao.findShopById(shop);
	}
	
	public ResultMessage findNotice(HttpSession httpSession) {
		Shop shop=new Shop();
		String businessmanId = httpSession.getAttribute("businessmanId").toString();
		if(businessmanId!=null){
			shop.setBusinessmanId(Integer.parseInt(businessmanId));
			return   new ResultMessage(true, ResultCode.SUCCESS, "获取成功",
					shopDao.findShopById(shop));
		
		}
		return new ResultMessage(false, ResultCode.NO_LOGIN, "失败",
				null);
		
	}
	@Override
	public void insertShop(Shop shop){
//		shopDao.insertShop(shop);
	}
	
	@Override
	public Shop findShopByName(String shopName){
		return shopDao.findShopByName(shopName);
	}
	@Override
	public void updateShopById(Shop shop) {
		System.out.println(shop.getShopNotice());
		shopDao.updateShopById(shop);
	}
	
	@Override
	public void updateDispatching(Shop shop) {
//		System.out.println();
		shopDao.updateDispatching(shop);
	}
	@Override
	public void updateAddress(Shop shops) {
		// TODO Auto-generated method stub
		shopDao.updateAddress(shops);
	}

}

