package com.chb.entity;
/**
 * ��Ʒ����ʵ����
 * @author ����Ԫ
 * 2017��11��30��
 *
 */
public class ClassifyGoods {
	private Integer id;
	private String classifyName;
	private String classifyDescription;
	private Integer shopId;
	
	public ClassifyGoods() {
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
