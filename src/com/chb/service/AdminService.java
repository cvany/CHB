package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.Admin;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.entity.User;

public interface AdminService {

	Admin findByAdminName(String adminname);
	//登录接口
	ResultMessage adminLogin(Admin admin,HttpSession session);
	//查询用户
	ResultMessage getUserListByPage(Page page);
	//删除用户
	ResultMessage deleteUserById(User user);
	//查询审核商家
	ResultMessage getBusinessInDataListByPage(Page page);
	//根据ID查询商家详情
	ResultMessage checkDetailsById(ShopInData shopInData);
	//审核通过
	ResultMessage checkBusinessOkById(Shop shop);
	//审核不通过
	ResultMessage checkBusinessFailById(Shop shop);
	


}
