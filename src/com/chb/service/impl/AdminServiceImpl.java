package com.chb.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.AdminDao;
import com.chb.entity.Admin;
import com.chb.entity.ResultMessage;
import com.chb.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public ResultMessage login(Admin admin, HttpSession session) {
		Admin mAdmin = adminDao.findByUserName(admin.getUserName());
		if (mAdmin != null && mAdmin.getPassword().equals(admin.getPassword())) {
			session.setAttribute("adminUserName", mAdmin.getUserName());
			mAdmin.setPassword(null);
			return new ResultMessage(true, ResultCode.SUCCESS, "登录成功", mAdmin);
		}
		return new ResultMessage(false, ResultCode.FAIL, "账号或者密码错误", null);
	}

	@Override
	public ResultMessage logout(HttpSession session) {
		session.invalidate();
		return new ResultMessage(false, ResultCode.SUCCESS, "注销成功", null);
	}

	@Override
	public ResultMessage isLogin(HttpSession session) {
		String userName = (String) session.getAttribute("adminUserName");
		if(userName!= null) {
			Admin mAdmin = adminDao.findByUserName(userName);
			mAdmin.setPassword(null);
			return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", mAdmin);
		}
		return new ResultMessage(false, ResultCode.NO_LOGIN, "未登录", null);
	}

}
