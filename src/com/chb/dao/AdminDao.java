package com.chb.dao;

import com.chb.entity.Admin;

/**
 * 管理员dao接口类
 * @author shilim
 *
 */
public interface AdminDao {
	// 通过用户名查询管理员 
	Admin findByUserName(String userName);
}
