package com.chb.entity;
/**
 * 商店实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */
public class Shop {
	private Integer id;
	private Integer businessmanId;
	private String shopName;
	private String shopPhoto;
	private String phone;
	private Integer credibility;
	private Double lng;
	private Double lat;
	private String address;
	private String legalRepresentative;
	private Integer isPass;
	private Integer isOnline;
	private Integer isOpen;
	private String openTime;
	private String shopNotice;
	private Double startPrice;
	private Double carriage;
	private Integer averageTime;
	private String dispatchDescription;
	private Integer takeoutAreaId;
	private Integer monthSales;
	private Integer shopPoint;
	private Integer shopClassifyId;
	//添加商家姓名属性商家分类名，联合查询
	private String businessmanName;
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Shop() {
	}
	
	public String getBusinessmanName() {
		return businessmanName;
	}

	public void setBusinessmanName(String businessmanName) {
		this.businessmanName = businessmanName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusinessmanId() {
		return businessmanId;
	}

	public void setBusinessmanId(Integer businessmanId) {
		this.businessmanId = businessmanId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPhoto() {
		return shopPhoto;
	}

	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCredibility() {
		return credibility;
	}

	public void setCredibility(Integer credibility) {
		this.credibility = credibility;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
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

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getShopNotice() {
		return shopNotice;
	}

	public void setShopNotice(String shopNotice) {
		this.shopNotice = shopNotice;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getCarriage() {
		return carriage;
	}

	public void setCarriage(Double carriage) {
		this.carriage = carriage;
	}

	public Integer getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(Integer averageTime) {
		this.averageTime = averageTime;
	}

	public String getDispatchDescription() {
		return dispatchDescription;
	}

	public void setDispatchDescription(String dispatchDescription) {
		this.dispatchDescription = dispatchDescription;
	}

	public Integer getTakeoutAreaId() {
		return takeoutAreaId;
	}

	public void setTakeoutAreaId(Integer takeoutAreaId) {
		this.takeoutAreaId = takeoutAreaId;
	}

	public Integer getMonthSales() {
		return monthSales;
	}

	public void setMonthSales(Integer monthSales) {
		this.monthSales = monthSales;
	}

	public Integer getShopPoint() {
		return shopPoint;
	}

	public void setShopPoint(Integer shopPoint) {
		this.shopPoint = shopPoint;
	}

	public Integer getShopClassifyId() {
		return shopClassifyId;
	}

	public void setShopClassifyId(Integer shopClassifyId) {
		this.shopClassifyId = shopClassifyId;
	}
	

}
