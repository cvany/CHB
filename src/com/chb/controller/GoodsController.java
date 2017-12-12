package com.chb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Goods;
import com.chb.entity.Shop;
import com.chb.service.GoodsService;

/**
 * 商品控制类
 * @author van元
 * 2017年12月12日
 *
 */
@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsServie;
	
	/**
	 * 根据商店id查找所有商品信息
	 * @param shop
	 * @return
	 */
	@RequestMapping("findAllGoodsByShopId")
	@ResponseBody
	public List<Goods> findAllGoodsByShopId(Shop shop){
		return goodsServie.findAllGoodsByShopId(shop);
	}
	
	/**
	 * 根据关键字模糊查询商品信息
	 * @param goods
	 * @return
	 */
	@RequestMapping("findGoodsByKeywords")
	@ResponseBody
	public List<Goods> findGoodsByKeywords(Goods goods){
		return goodsServie.findGoodsByKeywords(goods);
	}

}
