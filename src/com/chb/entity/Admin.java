package com.chb.entity;

/**
 * 管理员实体类
 * @author shilim
 *
 */
public class Admin {
	private Integer id;
	private String userName;
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin() {
		super();
	}
	public Admin(Integer id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
