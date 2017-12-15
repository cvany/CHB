package com.chb.dao;

import java.util.List;

import com.chb.entity.Message;
import com.chb.entity.Page;
import com.chb.entity.Shop;

public interface GoodsDao {
	//查询总页数
	long selectCount(Page page);
	//查询待审核商品
	List<Shop> getCheckGoodsListByPage(Page page);
	//审核商品通过
	Integer updateGoodsById(Integer id);
	//审核商品不通过
	Integer deleteGoodsById(Integer id);
	//返回商品审核信息
	Integer insertMessage(Message message);

}
