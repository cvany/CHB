package com.chb.entity;
/**
 * 商品分类实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */
public class ClassifyGoods {
	private Integer id;
	private String classifyName;
	private String classifyDescription;
	private Integer shopId;
	
	public ClassifyGoods() {
	}
	
	public ClassifyGoods(Integer id, String classifyName, String classifyDescription, Integer shopId) {
		super();
		this.id = id;
		this.classifyName = classifyName;
		this.classifyDescription = classifyDescription;
		this.shopId = shopId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getClassifyDescription() {
		return classifyDescription;
	}

	public void setClassifyDescription(String classifyDescription) {
		this.classifyDescription = classifyDescription;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
	

}
