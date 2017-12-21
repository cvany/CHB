
package com.chb.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.chb.entity.Admin;
import com.chb.entity.Message;

/**
 * 管理员dao接口类
 * @author 李卓宏
 *
 */
@Repository("AdminDao")
public interface AdminDao {

	Admin findByAdminName(String adminname);
	//返回信息
	Integer insertMessage(Message message);
	//查看管理日志
	List<String> getManagerLog();
	//增加数据分析结果
	Integer insertDataAnalysis(String data);
	//查看数据分析结果
	List<String> getDataAnalysis();
	
}
