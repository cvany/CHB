package com.chb.dao;

import com.chb.entity.Businessman;

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
}
