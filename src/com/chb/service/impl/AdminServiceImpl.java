package com.chb.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.chb.constant.ResultCode;
import com.chb.dao.AdminDao;
import com.chb.dao.BusinessmanDao;
import com.chb.dao.ComplaintDao;
import com.chb.dao.GoodsDao;
import com.chb.dao.OrderDao;
import com.chb.dao.ShopDao;
import com.chb.dao.ShopInDataDao;
import com.chb.dao.UserDao;
import com.chb.entity.Admin;
import com.chb.entity.Businessman;
import com.chb.entity.Complaint;
import com.chb.entity.Goods;
import com.chb.entity.Message;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.entity.User;
import com.chb.service.AdminService;

/**
 * 管理员服务接口实现类
 * @author 李卓宏
 *2017年12月6日
 */

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
	@Autowired
	private OrderDao orderDao;

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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(user.getId());
		message.setType(5);
		message.setContent(df.format(new Date())+":用户"+user.getUserName()+"已冻结");
		Integer inMessage=adminDao.insertMessage(message);
		if(deleteResult==null) {
			return new ResultMessage(true,ResultCode.SUCCESS,"冻结成功",deleteResult);
		}
		return new ResultMessage(false,ResultCode.FAIL,"冻结失败",null);
		
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(shop.getId());
		message.setType(5);
		message.setContent(df.format(new Date())+"商店"+shop.getShopName()+"通过审核");
		Integer inMessage=adminDao.insertMessage(message);
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(shop.getId());
		message.setType(5);
		message.setContent(df.format(new Date())+":商店"+shop.getShopName()+"未通过审核");
		Integer inMessage=adminDao.insertMessage(message);
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(shop.getId());
		message.setType(5);
		message.setContent(df.format(new Date())+":商店"+shop.getShopName()+"信息已修改");
		Integer inMessage=adminDao.insertMessage(message);
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(goods.getId());
		message.setType(5);
		message.setContent(df.format(new Date())+":商品"+goods.getGoodsName()+"通过审核");
		Integer inMessage=adminDao.insertMessage(message);
		if (updateResult == 1 ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", updateResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//审核商品不通过
	@Override
	public ResultMessage deleteGoodsById(Goods goods) {
		System.out.println(goods.getId()+"审核商品不通过服务接口");
		Integer deleteGoodsResult=goodsDao.deleteGoodsById(goods.getId());
		System.out.println(deleteGoodsResult);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(goods.getId());
		message.setType(5);
		message.setContent(df.format(new Date())+":商品"+
				goods.getGoodsName()+"不合要求，未通过审核");
		Integer inMessage=adminDao.insertMessage(message);
		if (deleteGoodsResult == 1  ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", deleteGoodsResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//审核投诉
	@Override
	public ResultMessage getDealComplainListByPage(Page page) {
		System.out.println("投 诉服务接口");
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(complaint.getShopId());
		message.setType(5);
		message.setContent(df.format(new Date())+":用户"+
				complaint.getUserName()+"的投诉信息"+complaint.getContent()+"已受理");
		Integer inMessage=adminDao.insertMessage(message);
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(complaint.getId());
		message.setType(5);
		message.setContent(df.format(new Date())+":用户"+
				complaint.getUserName()+"对商家"+complaint.getShopName()+"投诉信息无效");
		Integer inMessage=adminDao.insertMessage(message);
		if (deleteComplaintResult == 1  ) {
			return new ResultMessage(true, ResultCode.SUCCESS, "通过成功", deleteComplaintResult);
		}
		return new ResultMessage(false, ResultCode.FAIL, "审核失败", null);
		
	}
	//查看用户数据
	@Override
	public List<Long> getUserData() {
		System.out.println("查看用户");
		List<Long> userData=new ArrayList<>();
		for(long i=7;i<=12;i++) {		//根据时间段查询用户数量
			long total = userDao.selectCountByTime(i);
			userData.add(total);
		}
		for(long i=0;i<=5;i++) {		//根据登录次数查询用户数量
			long total = userDao.selectCountByLoginCount(i);
			userData.add(total);
		}
		return userData;
	}
	//查看管理日志
	@Override
	public List<String> getManagerLog() {
		System.out.println("查看管理日志服务接口");
		List<String> managerLog=adminDao.getManagerLog();
		
		return managerLog;
	}
	//欢迎页数据
	@Override
	public List<Long> getAllData() {
		System.out.println("查看所有数据服务接口");
		List<Long> allData=new ArrayList<>();
		allData.add(userDao.selectCount( null));
		allData.add(shopDao.selectAllCount(null));
		allData.add(orderDao.selectCountByPayMode(0)+orderDao.selectCountByPayMode(1));
		return allData;
	}
	
	//查看订单数据分析
	@Override
	public List<Long> getOrderData() {
		System.out.println("查看订单");
		List<Long> orderData=new ArrayList<>();
		for(long i=0;i<=5;i++) {		//根据订单价格查询
			long total = orderDao.selectCountByPrice(i);
			
			orderData.add(total);
		}
		for(long i=0;i<=1;i++) {		//根据订单价格查询
			long total = orderDao.selectCountByPayMode(i);
			
			orderData.add(total);
		}
		return orderData;
	}
	//查看商家数据分析
	@Override
	public List<Long> getBusinessData() {
		System.out.println("查看商家");
		List<Long> businessData=new ArrayList<>();
		for(long i=0;i<=4;i++) {		//根据商家信誉度查询
			long total = shopDao.selectCountByCredibility(i);
			
			businessData.add(total);
		}
		for(long i=1;i<=8;i++) {			//根据商家类型查询
			long total = shopDao.selectCountByShopClassifyId(i);
			
			businessData.add(total);
		}
		return businessData;
	}
	//增加数据分析记录
	@Override
	public String setDataAnalysis(String data) {
		System.out.println(data);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer insertResult=adminDao.insertDataAnalysis(df.format(new Date())+ data);
		System.out.println(insertResult);
		Message message=new Message();
		message.setSender(1);
		message.setReceiver(1);
		message.setType(5);
		message.setContent(df.format(new Date())+"进行了数据分析");
		Integer inMessage=adminDao.insertMessage(message);
		if(insertResult!=1) {
			return "0";
		}
		return "1";
	}
	//查看数据分析结果
	@Override
	public List<String> getDataAnalysis() {
		System.out.println("查看数据分析服务接口");
		List<String> dataAnalysis=adminDao.getDataAnalysis();
		
		return dataAnalysis;
	}

}
