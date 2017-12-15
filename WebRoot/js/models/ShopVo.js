if(typeof ShopVo == 'undefined') {
	function ShopVo() {
        this.id;
        this.businessmanId;
        this.shopName;
        this.shopPhoto;
        this.phone;
        this.lng;
        this.lat;
        this.address;
        this.isOnline;
        this.legalRepresentative;
        this.isPass;
        this.isOnline;
        this.isOpen;
        this.openTime;
        this.shopNotice;
        this.startPrice;
        this.carriage;
        this.averageTime;
        this.dispatchDescription;
        this.takeoutAreaId;
        this.monthSales;
        this.shopClassify;
	}
}

ShopVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}