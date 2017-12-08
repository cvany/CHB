package com.chb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Admin;
import com.chb.entity.Businessman;
import com.chb.entity.Goods;
import com.chb.entity.ResultMessage;
import com.chb.service.BusinessmanService;
import com.chb.service.GoodsService;
import com.chb.utils.JsonUtil;

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
}
