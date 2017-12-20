package com.chb.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.chb.entity.Admin;
import com.chb.entity.Complaint;
import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.entity.User;
/**
 * 管理员服务接口类
 * @author 李卓宏
 *2017年12月6日
 */
public interface AdminService {

	Admin findByAdminName(String adminname);
	//登录接口
	ResultMessage adminLogin(Admin admin,HttpSession session);
	//判定后台管理员是否登录
	ResultMessage adminIsLogin(HttpSession session);
	//后台管理员注销
	ResultMessage adminLogout(HttpSession session);
	//查询用户
	ResultMessage getUserListByPage(Page page);
	//删除用户
	ResultMessage deleteUserById(User user);
	//查询审核商家
	ResultMessage getBusinessInDataListByPage(Page page);
	//根据ID查询商家详情
	ResultMessage checkDetailsById(ShopInData shopInData);
	//审核商家通过
	ResultMessage checkBusinessOkById(Shop shop);
	//审核商家不通过
	ResultMessage deleteBusinessById(Shop shop);
	//查询所有商家
	ResultMessage getBusinessListByPage(Page page);
	//查询所有待审核商品
	ResultMessage getCheckGoodsListByPage(Page page);
	//审核商品通过
	ResultMessage checkGoodsOkById(Goods goods);
	//审核商品不通过
	ResultMessage deleteGoodsById(Goods goods);
	//分页查询审核投诉信息
	ResultMessage getDealComplainListByPage(Page page);
	//审核投诉信息通过
	ResultMessage checkComplaintOkById(Complaint complaint);
	//审核投诉信息不通过
	ResultMessage deleteComplaintById(Complaint complaint);
	//修改商家信息
	ResultMessage updateShopById(Shop shop);
	//查看用户数据
	List<Long> getUserData();
	//查看管理日志
	List<String> getManagerLog();
	//查看总数据
	List<Long> getAllData();
	//查看订单数据分析
	List<Long> getOrderData();
	//增加数据分析
	String setDataAnalysis(String data);
	//查看数据分析结果
	List<String> getDataAnalysis();
	//查看商家数据分析
	List<Long> getBusinessData();
	
	


}
