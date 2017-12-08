package com.chb.dao;

import com.chb.entity.Goods;

public interface GoodsDao {
	// 通过id查询Goods
	Goods selectByPrimaryKey(Integer id);
}
