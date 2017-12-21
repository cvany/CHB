package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.service.AdminService;
import com.chb.utils.JsonUtil;
/**
 * 管理员管理商品类
 * @author 李卓宏
 *2017年12月10日
 */
@Controller
public class AdminManageGoodsController {
	@Autowired
	private AdminService adminService;
	

	
	//管理员分页查询所有商品
	@RequestMapping("getCheckGoodsListByPage")          
	@ResponseBody
	public ResultMessage getCheckGoodsListByPage(String page ) throws Exception {
		System.out.println(page+"商品");
		return adminService.getCheckGoodsListByPage(JsonUtil.jsonToObject(page, Page.class));
	}
	
	//管理员审核通过商品
	@RequestMapping("checkGoodsOk")
	@ResponseBody
	public ResultMessage checkGoodsOk(String goods) throws Exception {
		System.out.println(goods+"审核通过");
		return adminService.checkGoodsOkById(JsonUtil.jsonToObject(goods , Goods.class));
	}
	
	//管理员审核不通过商品
	@RequestMapping("deleteGoods")
	@ResponseBody
	public ResultMessage deleteGoods(String goods) throws Exception {
		System.out.println(goods+"审核不通过");
		return adminService.deleteGoodsById(JsonUtil.jsonToObject(goods , Goods.class));
	}
}
