package com.chb.dao;

import java.util.List;

import com.chb.entity.Businessman;
import com.chb.entity.Page;
import com.chb.entity.User;

/**
 * 商家dao接口类
 * @author shilim
 *
 */
public interface BusinessmanDao {
	// 通过用户名查询商家 
	Businessman findByBusinessmanName(String businessmanName);
	// 通过手机号查询商家
	Businessman findBusnessByPhone(String phone);
//	//条件分页查询总数
//	long selectCount(Page page);
//	//条件分页查询审核商家
//	List<Businessman> selectUserByPage(Page page);
	
	
}