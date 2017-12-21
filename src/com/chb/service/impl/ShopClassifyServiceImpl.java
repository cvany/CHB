package com.chb.service.impl;

import java.util.ArrayList;
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

	public List<Shop> findShopByKeyWords(Shop shop) {
		return shopClassifyDao.findShopByKeyWords(shop);
	}

	public List<Shop> findShopIdByGoodsName(Shop shop) {
		return shopClassifyDao.findShopIdByGoodsName(shop);
	}

	public List<Shop> findShopByShopIdList(List<Shop> shop) {
		List<Shop> list =new ArrayList<>();
		for(int i=0;i<shop.size();i++){
			list.add(shopClassifyDao.findShopByShopIdList(shop.get(i)));
		}
		return list;
	}

	public List<Shop> findShopByShopClassifyId(ShopClassify sc) {
		return shopClassifyDao.findShopByShopClassifyId(sc);
	}

}
