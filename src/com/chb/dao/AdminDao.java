package com.chb.dao;

import com.chb.entity.Admin;

/**
 * 管理员dao接口类
 * @author 李卓宏
 *
 */
public interface AdminDao {

	Admin findByUserName(String adminname);
	
}
