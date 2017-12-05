package com.chb.entity;

/**
 * 结果信息类
 * @author shilim
 *
 */
public class ResultMessage{
	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 登陆标志
	 */
	private Boolean userToken;
	/**
	 * 操作结果
	 */
	private Integer serviceResult;
	/**
	 * 反馈信息
	 */
	private String resultInfo;
	/**
	 * 结果集合
	 */
	private Object resultParam;
	
	public ResultMessage() {
		super();
	}

	public ResultMessage(Boolean userToken, Integer serviceResult, String resultInfo, Object resultParam) {
		super();
		this.userToken = userToken;
		this.serviceResult = serviceResult;
		this.resultInfo = resultInfo;
		this.resultParam = resultParam;
	}

	public Boolean getUserToken() {
		return userToken;
	}
	public void setUserToken(Boolean userToken) {
		this.userToken = userToken;
	}
	public Integer getServiceResult() {
		return serviceResult;
	}
	public void setServiceResult(Integer serviceResult) {
		this.serviceResult = serviceResult;
	}
	public String getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	public Object getResultParam() {
		return resultParam;
	}
	public void setResultParam(Object resultParam) {
		this.resultParam = resultParam;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ResultMessage [userToken=" + userToken + ", serviceResult=" + serviceResult + ", resultInfo="
				+ resultInfo + ", resultParam=" + resultParam + "]";
	}
}
