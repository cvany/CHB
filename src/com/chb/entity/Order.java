package com.chb.entity;
/**
 * 订单实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */
import java.util.Date;
import java.util.List;

public class Order {
	private Integer id;
	private String orderNo;
	private Integer userId;
	private Integer shopId;
	private Integer status;
	private String notes;
	private String reson;
	private Double sumMoney;
	private Integer payMode;
	private Integer reminder;
	private Date createTime;
	private Date acceptTime;
	private Date shopConfirmTime;
	private Date userConfirmTime;
	private Integer isPay;
	private String paySerialsNumber;
	private User user;
	private Shop shop;
	private List<OrderGoodsList> OrderGoodsList;
	
	public Order() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public Integer getPayMode() {
		return payMode;
	}

	public void setPayMode(Integer payMode) {
		this.payMode = payMode;
	}

	public Integer getReminder() {
		return reminder;
	}

	public void setReminder(Integer reminder) {
		this.reminder = reminder;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getShopConfirmTime() {
		return shopConfirmTime;
	}

	public void setShopConfirmTime(Date shopConfirmTime) {
		this.shopConfirmTime = shopConfirmTime;
	}

	public Date getUserConfirmTime() {
		return userConfirmTime;
	}

	public void setUserConfirmTime(Date userConfirmTime) {
		this.userConfirmTime = userConfirmTime;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public String getPaySerialsNumber() {
		return paySerialsNumber;
	}

	public void setPaySerialsNumber(String paySerialsNumber) {
		this.paySerialsNumber = paySerialsNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<OrderGoodsList> getOrderGoodsList() {
		return OrderGoodsList;
	}

	public void setOrderGoodsList(List<OrderGoodsList> orderGoodsList) {
		OrderGoodsList = orderGoodsList;
	}
	
}
