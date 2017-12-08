package com.chb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.ShopClassifyDao;
import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.Shop;
import com.chb.entity.ShopClassify;
import com.chb.service.ShopClassifyService;
/**
 * 商店分类服务实现类
 * @author 崔文元
 * 2017年12月7日
 *
 */
@Service
public class ShopClassifyServiceImpl implements ShopClassifyService {
	@Autowired
	private ShopClassifyDao shopClassifyDao;
	
	public List<ShopClassify> findAllShopClassify() {
		return shopClassifyDao.findAllShopClassify();
	}

	public List<Shop> findShopByPage(Page page) {
		return shopClassifyDao.findShopByPage(page);
	}

	public List<Goods> findGoodsByShopId(Shop shop) {
		return shopClassifyDao.findGoodsByShopId(shop);
	}

}
