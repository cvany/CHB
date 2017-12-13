package com.chb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;

/**
 * 菜品服务接口类
 * 
 * @author shilim
 *
 */
public interface GoodsService {
	// 通过page获取菜品列表
	ResultMessage getGoodsByPage(Page page, HttpSession session);

	// 通过id获取菜品
	ResultMessage getGoodsById(Goods goods);

	// 添加菜品
	ResultMessage addGoods(Goods goods, HttpSession session, HttpServletRequest request);

	// 修改菜品
	ResultMessage updateGoods(Goods goods, HttpServletRequest request);

	// 删除菜品
	ResultMessage deleteGoods(List<Goods> goods);
}
