package com.chb.utils;

import java.lang.reflect.Type;
import java.util.Random;

import com.google.gson.Gson;

/**
 * 验证码生成工具类
 * @author cqs
 *
 */
public class ValidateCodeUtil {
	/**
	 * 生成0-10随机验证码
	 * @return String
	 */
	public static String validateCode(){
	return ((int)(Math.random()*(1000000-100000)+100000))+"";
		 
	}

}
