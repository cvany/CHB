package com.chb.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.constant.ResultCode;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.service.ShopInDataService;
import com.chb.utils.Base64Util;
import com.chb.utils.JsonUtil;

/**
 *商家入驻信息控制类
 * 
 * @author cqs
 *
 */
@Controller
public class ShopInDataController {
	@Autowired
	private ShopInDataService shopInDataService;

	//
	@RequestMapping("insertShopInData")
	@ResponseBody
	public void insertShopInData(String shopInData,HttpServletRequest request) throws Exception{
		ShopInData shopInDatas = new ShopInData();
		shopInDatas=JsonUtil.jsonToObject(shopInData, ShopInData.class);
		String shopPhotoUrl = "CHB_upload/shop/" + shopInDatas.getShopId() + "/shop/";
		String fpsaveUrl = request.getServletContext().getRealPath("/") + "../" + shopPhotoUrl + UUID.randomUUID() + ".jpg";
		String ipsaveUrl = request.getServletContext().getRealPath("/") + "../" + shopPhotoUrl + UUID.randomUUID() + ".jpg";
		String ifpsaveUrl = request.getServletContext().getRealPath("/") + "../" + shopPhotoUrl + UUID.randomUUID() + ".jpg";
		String ibpsaveUrl = request.getServletContext().getRealPath("/") + "../" + shopPhotoUrl + UUID.randomUUID() + ".jpg";
		String blsaveUrl = request.getServletContext().getRealPath("/") + "../" + shopPhotoUrl + UUID.randomUUID() + ".jpg";
		String cssaveUrl = request.getServletContext().getRealPath("/") + "../" + shopPhotoUrl + UUID.randomUUID() + ".jpg";
		boolean fp = Base64Util.GenerateImage(shopInDatas.getFrontPhoto(),fpsaveUrl);
		boolean ip = Base64Util.GenerateImage(shopInDatas.getInsidePhoto(),ipsaveUrl);
		boolean ifp = Base64Util.GenerateImage(shopInDatas.getIDFrontPhoto(),ifpsaveUrl);
		boolean ibp = Base64Util.GenerateImage(shopInDatas.getIDBackPhoto(),ibpsaveUrl);
		boolean bl = Base64Util.GenerateImage(shopInDatas.getBusinessLicense(),blsaveUrl);
		boolean cs = Base64Util.GenerateImage(shopInDatas.getCateringServiceLicense(),cssaveUrl);
		if(fp) {
			shopInDatas.setFrontPhoto(fpsaveUrl);
			shopInDatas.setInsidePhoto(ipsaveUrl);
			shopInDatas.setIDFrontPhoto(ifpsaveUrl);
			shopInDatas.setIDBackPhoto(ibpsaveUrl);
			shopInDatas.setBusinessLicense(blsaveUrl);
			shopInDatas.setCateringServiceLicense(cssaveUrl);
		    shopInDataService.insertShopInData(shopInDatas);
		}
	}
	
}
