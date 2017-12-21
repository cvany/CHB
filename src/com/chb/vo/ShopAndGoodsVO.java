package com.chb.vo;

public class ShopAndGoodsVO {
	public Integer shopId;
	public String shopPhoto;
	public String shopName;
	public Integer monthSales;
	public Double startPrice;
	public String shopNotice;
	public String goodsPhoto1;
	public String goodsPhoto2;
	public String goodsPhoto3;
	public String goodsName1;
	public String goodsName2;
	public String goodsName3;
	public Double price1;
	public Double price2;
	public Double price3;
	
	
	public ShopAndGoodsVO(Integer shopId, String shopPhoto, String shopName, Integer monthSales, Double startPrice,
			String shopNotice) {
		super();
		this.shopId = shopId;
		this.shopPhoto = shopPhoto;
		this.shopName = shopName;
		this.monthSales = monthSales;
		this.startPrice = startPrice;
		this.shopNotice = shopNotice;
	}
	
}
