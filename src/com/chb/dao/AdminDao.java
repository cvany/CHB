
package com.chb.dao;

import org.springframework.stereotype.Repository;

import com.chb.entity.Admin;

/**
 * 管理员dao接口类
 * @author shilim
 *
 */
@Repository("AdminDao")
public interface AdminDao {
	// 通过用户名查询管理员 
	Admin findByUserName(String userName);
}
