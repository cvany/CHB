package com.chb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chb.entity.ClassifyGoods;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.service.ClassifyGoodsService;
import com.chb.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;

/**
 * 菜品分类控制类
 * 
 * @author shilim
 *
 */
@RestController
public class ClassifyGoodsController {
	@Autowired
	private ClassifyGoodsService classifyGoodsService;

	@RequestMapping("business/getClassifyGoodsListByPage")
	public ResultMessage getClassifyGoodsListByPage(String page, HttpSession session) throws Exception {
		return classifyGoodsService.getClassifyGoodsListByPage(JsonUtil.jsonToObject(page, Page.class), session);
	}
	
	@RequestMapping("business/getClassifyGoodsById")
	public ResultMessage getClassifyGoodsById(String classifyGoods) throws Exception {
		return classifyGoodsService.getClassifyGoodsById(JsonUtil.jsonToObject(classifyGoods, ClassifyGoods.class));
	}

	@RequestMapping("business/addClassifyGoods")
	public ResultMessage addClassifyGoods(String classifyGoods, HttpSession session) throws Exception {
		return classifyGoodsService.addClassifyGoods(JsonUtil.jsonToObject(classifyGoods, ClassifyGoods.class),session);
	}

	@RequestMapping("business/updateClassifyGoods")
	public ResultMessage updateClassifyGoods(String classifyGoods) throws Exception {
		return classifyGoodsService.updateClassifyGoods(JsonUtil.jsonToObject(classifyGoods, ClassifyGoods.class));
	}

	@RequestMapping("business/deleteClassifyGoods")
	public ResultMessage deleteClassifyGoods(String classifyGoods) throws Exception {
		return classifyGoodsService
				.deleteClassifyGoods(JsonUtil.jsonToObject(classifyGoods, new TypeToken<List<ClassifyGoods>>() {
				}.getType()));
	}
}
