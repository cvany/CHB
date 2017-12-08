package com.chb.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.GoodsDao;
import com.chb.entity.Admin;
import com.chb.entity.Goods;
import com.chb.entity.ResultMessage;
import com.chb.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;

	@Override
	public Goods findByGoodsName(String goodsName){
		return goodsDao.findByGoodsName(goodsName);
	}
	
	@Override
	public Goods findByShopId(int shopId){
		return goodsDao.findByShopId(shopId);
	}
	
}
