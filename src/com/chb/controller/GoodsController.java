package com.chb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Admin;
import com.chb.entity.Goods;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.service.BusinessmanService;
import com.chb.service.GoodsService;
import com.chb.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import com.chb.entity.Page;

/**
 * 商家控制类
 * 
 * @author cqs
 *
 */
@Controller
public class GoodsController {
	@Autowired
	GoodsService goodsService;

	// 通过菜品名获取菜品信息
	@RequestMapping("findByGoodsName")
	@ResponseBody
	public Goods findByGoodsName(String goodsName){
		return goodsService.findByGoodsName(goodsName);
	}
	
	@RequestMapping("business/getGoodsListByPage")
	@ResponseBody
	public ResultMessage getGoodsListByPage(String page, HttpSession session) throws Exception {
		return goodsService.getGoodsByPage(JsonUtil.jsonToObject(page, Page.class), session);
	}

	@RequestMapping("business/getGoodsById")
	@ResponseBody
	public ResultMessage getGoodsById(String goods) throws Exception {
		return goodsService.getGoodsById(JsonUtil.jsonToObject(goods, Goods.class));
	}

	@RequestMapping("business/addGoods")
	@ResponseBody
	public ResultMessage addGoods(String goods, HttpSession session, HttpServletRequest request) throws Exception {
		return goodsService.addGoods(JsonUtil.jsonToObject(goods, Goods.class), session, request);
	}

	@RequestMapping("business/updateGoods")
	@ResponseBody
	public ResultMessage updateGoods(String goods, HttpServletRequest request) throws Exception {
		return goodsService.updateGoods(JsonUtil.jsonToObject(goods, Goods.class), request);
	}

	@RequestMapping("business/deleteGoods")
	@ResponseBody
	public ResultMessage deleteGoods(String goods) throws Exception {
		return goodsService.deleteGoods(JsonUtil.jsonToObject(goods, new TypeToken<List<Goods>>() {
		}.getType()));
	}
	
	/**
	 * 根据商店id查找所有商品信息
	 * @param shop
	 * @return
	 */
	@RequestMapping("findAllGoodsByShopId")
	@ResponseBody
	public List<Goods> findAllGoodsByShopId(Shop shop){
		return goodsService.findAllGoodsByShopId(shop);
	}
	
	/**
	 * 根据关键字模糊查询商品信息
	 * @param goods
	 * @return
	 */
	@RequestMapping("findGoodsByKeywords")
	@ResponseBody
	public List<Goods> findGoodsByKeywords(Goods goods){
		return goodsService.findGoodsByKeywords(goods);
	}
}
