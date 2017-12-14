package com.chb.entity;
/**
 * 商家诚信度实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */

import java.util.Date;

public class Credibility {
	private Integer id;
	private String reason;
	private Date recordTime;
	private Integer shopId;
	private Integer changePoint;
	
	public Credibility() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getChangePoint() {
		return changePoint;
	}

	public void setChangePoint(Integer changePoint) {
		this.changePoint = changePoint;
	}
	

}
