package com.chb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.GoodsDao;
import com.chb.entity.Goods;
import com.chb.entity.Shop;
import com.chb.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDao goodsDao;
	
	public List<Goods> findAllGoodsByShopId(Shop shop) {
		return goodsDao.findAllGoodsByShopId(shop);
	}

	public List<Goods> findGoodsByKeywords(Goods goods) {
		return goodsDao.findGoodsByKeywords(goods);
	}

}
