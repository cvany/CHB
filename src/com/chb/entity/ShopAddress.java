package com.chb.entity;
/**
 * �̵��ַʵ����
 * @author ����Ԫ
 * 2017��11��30��
 *
 */
public class ShopAddress {
	private Integer id;
	private String specificAddress;
	private Double lng;
	private Double lat;
	private Integer provinceId;
	private Integer cityId;
	private Integer areaId;
	
	public ShopAddress() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecificAddress() {
		return specificAddress;
	}

	public void setSpecificAddress(String specificAddress) {
		this.specificAddress = specificAddress;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

}
