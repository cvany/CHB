package com.chb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.ClassifyGoodsDao;
import com.chb.entity.ClassifyGoods;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.service.ClassifyGoodsService;

@Service
public class ClassifyGoodsServiceImpl implements ClassifyGoodsService {

	@Autowired
	ClassifyGoodsDao classifyGoodsDao;

	@Override
	public ResultMessage getClassifyGoodsListByPage(Page page, HttpSession session) {
		page.coutStartColum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("classifyGoods", new ClassifyGoods(null, null, null,
				Integer.parseInt(session.getAttribute("businessmanId").toString())));
		Long total = classifyGoodsDao.selectCountByPage(map);
		List<ClassifyGoods> classifyGoodsList = classifyGoodsDao.selectClassifyGoodsByPage(map);
		PageInfo<ClassifyGoods> pageInfo = new PageInfo<>(page, total, classifyGoodsList);
		return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", pageInfo);
	}

	@Override
	public ResultMessage getClassifyGoodsById(ClassifyGoods classifyGoods) {
		return new ResultMessage(true, ResultCode.SUCCESS, "获取成功",
				classifyGoodsDao.selectByPrimaryKey(classifyGoods.getId()));
	}

	@Override
	public ResultMessage addClassifyGoods(ClassifyGoods classifyGoods, HttpSession session) {
		classifyGoods.setShopId(Integer.parseInt(session.getAttribute("businessmanId").toString()));
		classifyGoodsDao.addClassifyGoods(classifyGoods);
		return new ResultMessage(true, ResultCode.SUCCESS, "添加成功", null);
	}

	@Override
	public ResultMessage updateClassifyGoods(ClassifyGoods classifyGoods) {
		classifyGoodsDao.updateClassifyGoods(classifyGoods);
		return new ResultMessage(true, ResultCode.SUCCESS, "修改成功", null);
	}

	@Override
	public ResultMessage deleteClassifyGoods(List<ClassifyGoods> classifyGoods) {
		classifyGoodsDao.deleteClassifyGoods(classifyGoods);
		return new ResultMessage(true, ResultCode.SUCCESS, "删除成功", null);
	}

}
