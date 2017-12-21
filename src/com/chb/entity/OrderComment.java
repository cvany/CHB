package com.chb.entity;
/**
 * 订单评论实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */

import java.util.Date;

public class OrderComment {
	private Integer id;
	private Integer shopId;
	private Integer orderId;
	private Integer userId;
	private Integer servicePoint;
	private Date time;
	private String content;
	
	public OrderComment() {
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getServicePoint() {
		return servicePoint;
	}

	public void setServicePoint(Integer servicePoint) {
		this.servicePoint = servicePoint;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
