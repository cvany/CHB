package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.Admin;
import com.chb.entity.ResultMessage;

public interface AdminService {

	Admin findByAdminName(String adminname);

	ResultMessage adminLogin(Admin admin,HttpSession session);
	


}
