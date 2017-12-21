package com.chb.dao;

import java.util.List;

import com.chb.entity.Complaint;
import com.chb.entity.Page;
import com.chb.entity.Shop;

public interface ComplaintDao {
	//查询总页数
	long selectCount(Page page);
	//分页查询所有投诉信息
	List<Complaint> getDealComplainListByPage(Page page);
	//审核投诉信息通过
	Integer updateComplaintById(Integer id);
	//审核投诉 信息不通过
	Integer deleteComplaintById(Integer id);

}
