package com.chb.entity;
/**
 * 商品实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */
public class Goods {
	private Integer id;
	private Integer shopId;
	private String goodsName;
	private Double goodsPrice;
	private String goodsPhoto;
	private String goodsDescription;
	private Integer goodsSales;
	private Integer isPass;
	private Integer isOnline;
	private Integer goodsPoint;
	private Integer classifyGoodsId;
	private Integer goodsNumber;
	private Integer goodsRemain;
	//添加商店名与商品分类名，联合查询
	private String shopName;
	private String classifyName;
	
	public Goods() {
	}
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsPhoto() {
		return goodsPhoto;
	}

	public void setGoodsPhoto(String goodsPhoto) {
		this.goodsPhoto = goodsPhoto;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public Integer getGoodsSales() {
		return goodsSales;
	}

	public void setGoodsSales(Integer goodsSales) {
		this.goodsSales = goodsSales;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getGoodsPoint() {
		return goodsPoint;
	}

	public void setGoodsPoint(Integer goodsPoint) {
		this.goodsPoint = goodsPoint;
	}

	public Integer getClassifyGoodsId() {
		return classifyGoodsId;
	}

	public void setClassifyGoodsId(Integer classifyGoodsId) {
		this.classifyGoodsId = classifyGoodsId;
	}

	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public Integer getGoodsRemain() {
		return goodsRemain;
	}

	public void setGoodsRemain(Integer goodsRemain) {
		this.goodsRemain = goodsRemain;
	}
	

}
