package com.chb.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.AdminDao;
import com.chb.dao.UserDao;
import com.chb.entity.Admin;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;
import com.chb.service.AdminService;



@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;

	public Admin findByAdminName(String adminname) {
		return adminDao.findByAdminName(adminname);
	}
	
	//管理员登录
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
	//管理员查询所有用户
	@Override
	public ResultMessage getUserListByPage(Page page) {
		page.coutStartColum();
		long total = userDao.selectCount(page);
		List<User> userList = userDao.selectUserByPage(page);
		PageInfo<User> pageInfo = new PageInfo<User>(page, total, userList);
		return new ResultMessage(true, ResultCode.SUCCESS,"成功", pageInfo);
	}
	
	@Override
	public ResultMessage deleteUserById(User user) {
		System.out.println(user.getId()+"删除用户服务接口");
		Integer deleteResult=userDao.deleteUserById(user.getId());
		System.out.println(deleteResult);
		if(deleteResult==null) {
			return new ResultMessage(true,ResultCode.SUCCESS,"删除3成功",deleteResult);
		}
		return new ResultMessage(false,ResultCode.FAIL,"删除失败",null);
		
	}

}
