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

	public Admin findByAdminName(String adminname) {
		return adminDao.findByAdminName(adminname);
	}
	
	@Override
	public ResultMessage adminLogin(Admin admin, HttpSession session) {
		System.out.println(admin.getUserName());
		Admin mAdmin = adminDao.findByAdminName(admin.getUserName());
		
		if (mAdmin != null && mAdmin.getPassword().equals(admin.getPassword())) {
			session.setAttribute("admin", mAdmin.getUserName());
			mAdmin.setPassword(null);
			return new ResultMessage(true, ResultCode.SUCCESS, "登录成功", mAdmin);
		}
		return new ResultMessage(false, ResultCode.FAIL, "账号或者密码错误", null);
	}

}
