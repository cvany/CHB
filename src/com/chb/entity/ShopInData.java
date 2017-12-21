package com.chb.entity;
/**
 * 商家入驻实体类
 * @author 崔文元
 * 2017年11月30日
 *
 */
public class ShopInData {
	private Integer id;
	private Integer shopId;
	private String frontPhoto;
	private String insidePhoto;
	private String IDFrontPhoto;
	private String IDBackPhoto;
	private String legalRepresentative;
	private String businessLicense;
	private String cateringServiceLicense;

	public ShopInData() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getFrontPhoto() {
		return frontPhoto;
	}

	public void setFrontPhoto(String frontPhoto) {
		this.frontPhoto = frontPhoto;
	}

	public String getInsidePhoto() {
		return insidePhoto;
	}

	public void setInsidePhoto(String insidePhoto) {
		this.insidePhoto = insidePhoto;
	}

	public String getIDFrontPhoto() {
		return IDFrontPhoto;
	}

	public void setIDFrontPhoto(String iDFrontPhoto) {
		IDFrontPhoto = iDFrontPhoto;
	}

	public String getIDBackPhoto() {
		return IDBackPhoto;
	}

	public void setIDBackPhoto(String iDBackPhoto) {
		IDBackPhoto = iDBackPhoto;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getCateringServiceLicense() {
		return cateringServiceLicense;
	}

	public void setCateringServiceLicense(String cateringServiceLicense) {
		this.cateringServiceLicense = cateringServiceLicense;
	}
	

}
