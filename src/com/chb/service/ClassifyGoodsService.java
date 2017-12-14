package com.chb.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.chb.entity.ClassifyGoods;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;

/**
 * 菜品分类服务接口类
 * 
 * @author shilim
 *
 */
public interface ClassifyGoodsService {
	// 分页获取菜品分类列表
	ResultMessage getClassifyGoodsListByPage(Page page, HttpSession session);
	
	// 通过id获取菜品分类
	ResultMessage getClassifyGoodsById(ClassifyGoods classifyGoods);

	// 添加菜品分类
	ResultMessage addClassifyGoods(ClassifyGoods classifyGoods, HttpSession session);

	// 修改菜品分类
	ResultMessage updateClassifyGoods(ClassifyGoods classifyGoods);

	// 删除菜品分类
	ResultMessage deleteClassifyGoods(List<ClassifyGoods> classifyGoods);
}
