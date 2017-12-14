package com.chb.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.AdminDao;
import com.chb.dao.BusinessmanDao;
import com.chb.dao.ComplaintDao;
import com.chb.dao.GoodsDao;
import com.chb.dao.ShopDao;
import com.chb.dao.ShopInDataDao;
import com.chb.dao.UserDao;
import com.chb.entity.Admin;
import com.chb.entity.Businessman;
import com.chb.entity.Complaint;
import com.chb.entity.Goods;
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
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ComplaintDao complaintDao;

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
	//后台管理员是否登录
	@Override
	public ResultMessage adminIsLogin(HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		System.out.println(userName);
		if(userName!= null) {
			System.out.println("dhkdhkjdj");
			Admin mAdmin = adminDao.findByAdminName(userName);
			mAdmin.setPassword(null);
			return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", mAdmin);
		}
		return new ResultMessage(false, ResultCode.NO_LOGIN, "未登录", null);
	}
	//后台管理员注销
	@Override
	public ResultMessage adminLogout(HttpSession session) {
		session.invalidate();
		return new ResultMessage(false, ResultCode.SUCCESS, "注销成功", null);
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
		if (checkBusinessResult ==1) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", checkBusinessResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//审核不通过
	@Override
	public ResultMessage deleteBusinessById(Shop shop) {
		System.out.println(shop.getId()+"删除商家服务接口");
		Integer deleteShopResult=shopDao.deleteShopById(shop.getId());
		System.out.println(deleteShopResult);
		if (deleteShopResult == 1  ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", deleteShopResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//管理员查询所有商家
	@Override
	public ResultMessage getBusinessListByPage(Page page) {
		System.out.println("商家服务接口");
		page.coutStartColum();
		long total = shopDao.selectAllCount(page);
		List<Shop> businessInDataList = shopDao.getBusinessListByPage(page);
		PageInfo<Shop> pageInfo = new PageInfo<Shop>(page, total, businessInDataList);
		return new ResultMessage(true, ResultCode.SUCCESS,"成功", pageInfo);
	}
	//修改商家信息
	@Override
	public ResultMessage updateShopById(Shop shop) {
		System.out.println(shop.getId()+shop.getCredibility()+shop.getIsOnline()+shop.getIsPass()+"修改商家服务接口");
		Shop checkBusinessResult=shopDao.updateShop(shop);
		System.out.println(checkBusinessResult);
		if (checkBusinessResult !=null) {
			return new ResultMessage(true, ResultCode.SUCCESS, "修改成功", checkBusinessResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "修改失败", null);
		
	}
	//管理员查询所有商品
	@Override
	public ResultMessage getCheckGoodsListByPage(Page page) {
		System.out.println("商品服务接口");
		page.coutStartColum();
		long total = goodsDao.selectCount(page);
		List<Shop> checkGoodsList = goodsDao.getCheckGoodsListByPage(page);
		PageInfo<Shop> pageInfo = new PageInfo<Shop>(page, total, checkGoodsList);
		return new ResultMessage(true, ResultCode.SUCCESS,"成功", pageInfo);
	}
	
	
	//审核商品通过
	@Override
	public ResultMessage checkGoodsOkById(Goods goods) {
		System.out.println(goods.getId()+"审核商品通过服务接口");
		Integer updateResult=goodsDao.updateGoodsById(goods.getId());
		System.out.println(updateResult);
		if (updateResult == 1 ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", updateResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//审核商品不通过
	@Override
	public ResultMessage deleteGoodsById(Goods goods) {
		System.out.println(goods.getId()+"删除商家服务接口");
		Integer deleteGoodsResult=goodsDao.deleteGoodsById(goods.getId());
		System.out.println(deleteGoodsResult);
		if (deleteGoodsResult == 1  ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", deleteGoodsResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//审核投诉
	@Override
	public ResultMessage getDealComplainListByPage(Page page) {
		System.out.println("投诉服务接口");
		page.coutStartColum();
		long total = complaintDao.selectCount(page);
		List<Complaint> checkGoodsList = complaintDao.getDealComplainListByPage(page);
		PageInfo<Complaint> pageInfo = new PageInfo<Complaint>(page, total, checkGoodsList);
		return new ResultMessage(true, ResultCode.SUCCESS,"成功", pageInfo);
	}
	
	//审核投诉通过
	@Override
	public ResultMessage checkComplaintOkById(Complaint complaint) {
		System.out.println(complaint.getId()+"审核投诉通过服务接口");
		Integer updateResult=complaintDao.updateComplaintById(complaint.getId());
		System.out.println(updateResult);
		if (updateResult == 1 ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", updateResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//审核投诉不通过
	@Override
	public ResultMessage deleteComplaintById(Complaint complaint) {
		System.out.println(complaint.getId()+"删除投诉信息服务接口");
		Integer deleteComplaintResult=complaintDao.deleteComplaintById(complaint.getId());
		System.out.println(deleteComplaintResult);
		if (deleteComplaintResult == 1  ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", deleteComplaintResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}

}
