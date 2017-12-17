if(typeof ShopVo == 'undefined') {
	function ShopVo() {
		this.id;
		this.shopName;
		this.credibility;
		this.isPass;
		this.isOnline;
	}
}

ShopVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}