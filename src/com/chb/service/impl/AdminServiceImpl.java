package com.chb.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.AdminDao;
import com.chb.dao.BusinessmanDao;
import com.chb.dao.ShopDao;
import com.chb.dao.ShopInDataDao;
import com.chb.dao.UserDao;
import com.chb.entity.Admin;
import com.chb.entity.Businessman;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.entity.User;
import com.chb.service.AdminService;



@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BusinessmanDao businessmanDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopInDataDao shopInDataDao;

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
	//删除用户
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
	
	//管理员查询所有待审核商家
	@Override
	public ResultMessage getBusinessInDataListByPage(Page page) {
		System.out.println("审核商家服务接口");
		page.coutStartColum();
		long total = shopDao.selectCount(page);
		List<Shop> businessInDataList = shopDao.getBusinessInDataListByPage(page);
		PageInfo<Shop> pageInfo = new PageInfo<Shop>(page, total, businessInDataList);
		return new ResultMessage(true, ResultCode.SUCCESS,"成功", pageInfo);
	}
	//根据ID查询商家详情
	@Override
	public ResultMessage checkDetailsById(ShopInData shopInData) {
		System.out.println("查询商家详情服务接口");
		ShopInData mShopInData = shopInDataDao.findByShopId(shopInData.getId());
		
		if (mShopInData != null ) {
			
			return new ResultMessage(true, ResultCode.SUCCESS, "查询商家详情成功", mShopInData);
		}
		return new ResultMessage(false, ResultCode.FAIL, "查询失败", null);
		
	}
	//审核通过
	@Override
	public ResultMessage checkBusinessOkById(Shop shop) {
		System.out.println(shop.getId()+"审核通过服务接口");
		Integer checkBusinessResult=shopDao.updateShopById(shop.getId());
		System.out.println(checkBusinessResult);
		if (checkBusinessResult == 1 ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", checkBusinessResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//审核通过
	@Override
	public ResultMessage checkBusinessFailById(Shop shop) {
		System.out.println(shop.getId()+"审核不通过服务接口");
		Integer deleteShopResult=shopDao.deleteShopById(shop.getId());
		System.out.println(deleteShopResult);
		if (deleteShopResult == 1  ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", deleteShopResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}

}
