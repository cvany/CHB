package com.chb.constant;

/**
 * 结果代码
 * @author shilim
 *
 */
public interface ResultCode {
	//操作成功
	Integer SUCCESS = 1;
	//操作失败
	Integer FAIL = 2;
	//参数错误
	Integer ILLEGAL_ARGUMENT = 3;
	//登录失败
	Integer NO_LOGIN = 4;
	//没有权限登录
	Integer NO_PERMISSION = 5;
}
