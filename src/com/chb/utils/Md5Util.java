package com.chb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * MD5加密工具类
 * @author 崔文元
 * 2017年12月6日
 *
 */
public class Md5Util {

	public static String encode(String plainText)  
    {  
        System.out.println("加密前:"+plainText);  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte[] b = md.digest();  
            int i;  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++)   
            {  
                i = b[offset];  
                if (i<0) i+=256;  
                if(i<16)  
                buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            System.out.println("加密后："+buf.toString());  
            return buf.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return plainText;  
    } 
}
