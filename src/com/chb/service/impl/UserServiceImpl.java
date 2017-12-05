package com.chb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.UserDao;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;
import com.chb.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByUserPhone(String phone) {
		return userDao.findByUserPhone(phone);
	}

	@Override
	public ResultMessage getUserListByPage(Page page) {
		page.coutStartColum();
		long total = userDao.selectCount(page);
		List<User> userList = userDao.selectUserByPage(page);
		PageInfo<User> pageInfo = new PageInfo<User>(page, total, userList);
		return new ResultMessage(true, ResultCode.SUCCESS,"成功", pageInfo);
	}
}
