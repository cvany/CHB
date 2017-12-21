package com.chb.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.BusinessmanDao;
import com.chb.entity.Businessman;
import com.chb.entity.ResultMessage;
import com.chb.service.BusinessmanService;

@Service
public class BusinessmanServiceImpl implements BusinessmanService {
	@Autowired
	private BusinessmanDao businessmanDao;

	@Override
	public ResultMessage login(Businessman businessman, HttpSession session) {
		Businessman mBusinessman = businessmanDao.findByBusinessmanPhone(businessman.getPhone());
		if (mBusinessman != null && mBusinessman.getPassword().equals(businessman.getPassword())) {
			session.setAttribute("businessmanId", mBusinessman.getId());
			session.setAttribute("businessmanName", mBusinessman.getPhone());
			mBusinessman.setPassword(null);
			return new ResultMessage(true, ResultCode.SUCCESS, "登录成功", mBusinessman);
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
		String phone = (String) session.getAttribute("businessmanName");
		if(phone!= null) {
			Businessman mBusinessman = businessmanDao.findByBusinessmanPhone(phone);
			mBusinessman.setPassword(null);
			return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", mBusinessman);
		}
		return new ResultMessage(false, ResultCode.NO_LOGIN, "未登录", null);
	}
	
	@Override
	public Businessman findByBusinessmanName(String businessmanName){
		return businessmanDao.findByBusinessmanName(businessmanName);
	}
	
	@Override
	public Businessman findByBusinessmanPhone(String phone){
		return businessmanDao.findByBusinessmanPhone(phone);
	}
	@Override
	public void insertBusinessman(Businessman businessman){
		businessmanDao.insertBusinessman(businessman);
	}

	@Override
	public Businessman findById(int id) {
		// TODO Auto-generated method stub
		return businessmanDao.findById(id);
	}

}
