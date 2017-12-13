package com.chb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Businessman;
import com.chb.entity.ResultMessage;
import com.chb.service.BusinessmanService;
import com.chb.utils.JsonUtil;

/**
 * 商家控制类
 * 
 * @author shilim
 *
 */
@Controller
public class BusinessmanController {
	@Autowired
	private BusinessmanService businessmanService;

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
}
