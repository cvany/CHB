package com.chb.entity;
/**
 * 商家实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */

import java.util.Date;

public class Businessman {
	private Integer id;
	private String businessmanName;
	private String password;
	private String salt;
	private String phone;
	private String email;
	private Date registerTime;
	private String photo;
	private Double accountBalance;

	public Businessman() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusinessmanName() {
		return businessmanName;
	}

	public void setBusinessmanName(String businessmanName) {
		this.businessmanName = businessmanName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

}
