package com.chb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Businessman;
import com.chb.entity.ResultMessage;
import com.chb.entity.ShopInData;
import com.chb.service.BusinessmanService;
import com.chb.utils.IndustrySMS;
import com.chb.utils.JsonUtil;
import com.chb.utils.ValidateCodeUtil;

/**
 * 商家控制类
 * 
 * @author shilim
 *
 */
@Controller
public class BusinessmanController {
	
	private String params="";
	@Autowired
	BusinessmanService businessmanService;

	// 管理员登录接口
	@RequestMapping("business/login")
	@ResponseBody
	public ResultMessage login(String businessman, HttpSession session) throws Exception {
		return businessmanService.login(JsonUtil.jsonToObject(businessman, Businessman.class), session);
	}

	// 管理员注销接口
	@RequestMapping("business/logout")
	@ResponseBody
	public ResultMessage logout(HttpSession session) throws Exception {
		return businessmanService.logout(session);
	}

	// 通过用户名获取管理员信息
	@RequestMapping("business/isLogin")
	@ResponseBody
	public ResultMessage isLogin(HttpSession session) throws Exception {
		return businessmanService.isLogin(session);
	}
	
	//存入一个商家
	@RequestMapping("insertBusinessman")
	@ResponseBody
	public int insertBusinessman(String businessman) throws Exception{
		Businessman business = new Businessman();
		business = JsonUtil.jsonToObject(businessman, Businessman.class);
		businessmanService.insertBusinessman(business);
		Businessman bp = businessmanService.findByBusinessmanPhone(business.getPhone());
		int id=0;
		if(bp!=null)
		id=bp.getId();
		return id;
	}
	
	//生成短信验证码
	@RequestMapping("industrySMS")
	@ResponseBody
	public void industrySMS(String businessman) throws Exception{
		Businessman business = new Businessman();
		business=JsonUtil.jsonToObject(businessman, Businessman.class);
		String to = business.getPhone();
		String param = ValidateCodeUtil.validateCode();
		IndustrySMS.execute(to,param);
		params=param;
	}
	//检验验证码
	@RequestMapping("checkValidateCode")
	@ResponseBody
	public boolean checkValidateCode(String validateCode) throws Exception{
		boolean status = params.equals(validateCode);
		params="";
		//测试
		status=true;
		return status;
	}
//获取配送信息
	@RequestMapping("getDispatchingByBusinessmanId")
	@ResponseBody
	public Businessman getDispatchingByBusinessmanId(int id) throws Exception{
		Businessman businessman= businessmanService.findById(id);
		return businessman;
	}
}
