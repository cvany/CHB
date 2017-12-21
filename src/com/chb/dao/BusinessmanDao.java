package com.chb.dao;

import com.chb.entity.Businessman;

/**
 * 商家dao接口类
 * @author shilim
 *
 */
public interface BusinessmanDao {
	// 通过id查询商家
	Businessman findById(Integer id);
	// 通过用户名查询商家 
	Businessman findByBusinessmanName(String businessmanName);
	// 通过手机号查询商家
	Businessman findByBusinessmanPhone(String phone);
	//存入商家
	void insertBusinessman(Businessman businessman);
	//id查找
	Businessman findById(int id);
//	//条件分页查询总数
//	long selectCount(Page page);
//	//条件分页查询审核商家
//	List<Businessman> selectUserByPage(Page page);
	
	
}