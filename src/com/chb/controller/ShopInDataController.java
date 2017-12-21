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
		String fpsaveUrl =shopPhotoUrl + UUID.randomUUID() + ".jpeg";
		String ipsaveUrl =shopPhotoUrl + UUID.randomUUID() + ".jpeg";
		String ifpsaveUrl =shopPhotoUrl + UUID.randomUUID() + ".jpeg";
		String ibpsaveUrl =shopPhotoUrl + UUID.randomUUID() + ".jpeg";
		String blsaveUrl =shopPhotoUrl + UUID.randomUUID() + ".jpeg";
		String cssaveUrl =shopPhotoUrl + UUID.randomUUID() + ".jpeg";
		
		String cfpsaveUrl = request.getServletContext().getRealPath("/") + "../" + fpsaveUrl;
		String cipsaveUrl = request.getServletContext().getRealPath("/") + "../" + ipsaveUrl;
		String cifpsaveUrl = request.getServletContext().getRealPath("/") + "../" + ifpsaveUrl;
		String cibpsaveUrl = request.getServletContext().getRealPath("/") + "../" + ibpsaveUrl;
		String cblsaveUrl = request.getServletContext().getRealPath("/") + "../" + blsaveUrl;
		String ccssaveUrl = request.getServletContext().getRealPath("/") + "../" + cssaveUrl;
		
		boolean fp = Base64Util.GenerateImage(shopInDatas.getFrontPhoto(),cfpsaveUrl);
		boolean ip = Base64Util.GenerateImage(shopInDatas.getInsidePhoto(),cipsaveUrl);
		boolean ifp = Base64Util.GenerateImage(shopInDatas.getIDFrontPhoto(),cifpsaveUrl);
		boolean ibp = Base64Util.GenerateImage(shopInDatas.getIDBackPhoto(),cibpsaveUrl);
		boolean bl = Base64Util.GenerateImage(shopInDatas.getBusinessLicense(),cblsaveUrl);
		boolean cs = Base64Util.GenerateImage(shopInDatas.getCateringServiceLicense(),ccssaveUrl);
		if(fp&ip&ifp&ibp&bl&cs) {
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
