package com.chb.dao;

import java.util.List;

import com.chb.entity.Admin;
import com.chb.entity.Message;

/**
 * 管理员dao接口类
 * @author 李卓宏
 *
 */
public interface AdminDao {

	Admin findByAdminName(String adminname);
	//返回信息
	Integer insertMessage(Message message);
	//查看管理日志
	List<String> getManagerLog();
	
}
