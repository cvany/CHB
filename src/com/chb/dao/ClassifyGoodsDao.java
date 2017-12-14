package com.chb.dao;

import java.util.List;
import java.util.Map;

import com.chb.entity.ClassifyGoods;

/**
 * 菜品分类dao接口类
 * @author shilim
 *
 */
public interface ClassifyGoodsDao {
	// 通过id查询分类
	ClassifyGoods selectByPrimaryKey(Integer id);
	// 分页查询分类总数
	Long selectCountByPage(Map<String, Object> map);
	// 分页查询分类列表
	List<ClassifyGoods> selectClassifyGoodsByPage(Map<String, Object> map);
	// 添加分类
	void addClassifyGoods(ClassifyGoods classifyGoods);
	// 修改分类
	void updateClassifyGoods(ClassifyGoods classifyGoods);
	// 删除分类
	void deleteClassifyGoods(List<ClassifyGoods> classifyGoods);
}
