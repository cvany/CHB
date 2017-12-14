package com.chb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.AdminDao;
import com.chb.entity.Admin;
import com.chb.service.AdminService;



@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	public Admin findByUserName(String adminname) {
		return adminDao.findByUserName(adminname);
	}

}
