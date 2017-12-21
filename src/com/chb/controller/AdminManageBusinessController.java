package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.service.AdminService;
import com.chb.utils.JsonUtil;
/**
 * 管理员管理商家类
 * @author 李卓宏
 *2017年12月8日
 */
@Controller
public class AdminManageBusinessController {
	@Autowired
	private AdminService adminService;
	
	//管理员分页查询所有待审核商家
	@RequestMapping("getBusinessInDataListByPage")          
	@ResponseBody
	public ResultMessage getBusinessInDataListByPage(String page ) throws Exception {
		System.out.println(page+"审核商家");
		return adminService.getBusinessInDataListByPage(JsonUtil.jsonToObject(page, Page.class));
	}
	//管理员分页查询所有待审核商家信息
	@RequestMapping("checkDetails")          
	@ResponseBody
	public ResultMessage checkDetailsById(String shopInData ) throws Exception {
		System.out.println(shopInData+"审核商家");
		return adminService.checkDetailsById(JsonUtil.jsonToObject(shopInData, ShopInData.class));
	}
	//管理员审核通过商家
	@RequestMapping("checkBusinessOk")
	@ResponseBody
	public ResultMessage checkBusinessOk(String shop) throws Exception {
		System.out.println(shop+"审核通过");
		return adminService.checkBusinessOkById(JsonUtil.jsonToObject(shop , Shop.class));
	}
	//管理员审核不通过商家
	@RequestMapping("deleteBusiness")
	@ResponseBody
	public ResultMessage deleteBusiness(String shop) throws Exception {
		System.out.println(shop+"审核不通过");
		return adminService.deleteBusinessById(JsonUtil.jsonToObject(shop , Shop.class));
	}
	//管理员分页查询所有商家
	@RequestMapping("getBusinessListByPage")          
	@ResponseBody
	public ResultMessage getBusinessListByPage(String page ) throws Exception {
		System.out.println(page+"商家");
		return adminService.getBusinessListByPage(JsonUtil.jsonToObject(page, Page.class));
	}
	//管理员修改商家
	@RequestMapping("updateShop")
	@ResponseBody
	public ResultMessage updateShop(String shop) throws Exception {
		System.out.println(shop+"审核通过");
		return adminService.updateShopById(JsonUtil.jsonToObject(shop , Shop.class));
	}

}
