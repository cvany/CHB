package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Complaint;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.service.AdminService;
import com.chb.utils.JsonUtil;
/**
 * 管理员管理投诉类
 * @author 李卓宏
 *2017年12月12日
 */
@Controller
public class AdminManageComplaintController {
	@Autowired
	private AdminService adminService;
	

	
	
	//管理员查询投诉信息
	@RequestMapping("getDealComplainListByPage")
	@ResponseBody
	public ResultMessage getDealComplainListByPage(String page) throws Exception {
		System.out.println(page+"投诉信息");
		return adminService.getDealComplainListByPage(JsonUtil.jsonToObject(page , Page.class));
	}
	
	//管理员审核通过投诉
	@RequestMapping("checkComplaintOk")
	@ResponseBody
	public ResultMessage checkComplaintOk(String complaint) throws Exception {
		System.out.println(complaint+"审核通过");
		return adminService.checkComplaintOkById(JsonUtil.jsonToObject(complaint , Complaint.class));
	}
	
	//管理员审核不通过投诉
	@RequestMapping("deleteComplaint")
	@ResponseBody
	public ResultMessage deleteComplaint(String complaint) throws Exception {
		System.out.println(complaint+"审核不通过");
		return adminService.deleteComplaintById(JsonUtil.jsonToObject(complaint , Complaint.class));
	}

}
