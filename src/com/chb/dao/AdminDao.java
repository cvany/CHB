
package com.chb.dao;

import org.springframework.stereotype.Repository;

import com.chb.entity.Admin;

/**
 * 管理员dao接口类
 * @author 李卓宏
 *
 */
@Repository("AdminDao")
public interface AdminDao {

	Admin findByUserName(String adminname);
	
}
