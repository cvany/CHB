if(typeof ShopInDataVo == 'undefined') {
	function ShopInDataVo() {
        this.id;
        this.shopId;
        this.frontPhoto;
        this.insidePhoto;
        this.IDFrontPhoto;
        this.IDBackPhoto;
        this.legalRepresentative;
        this.businessLicense;
        this.cateringServiceLicense;
	}
}

ShopInDataVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}