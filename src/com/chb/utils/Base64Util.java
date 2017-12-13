package com.chb.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

public class Base64Util {
	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr, String saveUrl) {
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr.substring(imgStr.indexOf(",")+1));
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String dirUrl = saveUrl.substring(0, saveUrl.lastIndexOf("/")).replace("/", File.separator);
			File file = new File(dirUrl);
			if(!file.exists() && !file.isDirectory()) {
				file.mkdirs();
				System.out.println("新建文件夹");
			}
			OutputStream out = new FileOutputStream(saveUrl);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
