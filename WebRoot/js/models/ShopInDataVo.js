if(typeof ShopInDataVo == 'undefined') {
	function ShopInDataVo() {
		this.id;
		
	}
}

ShopInDataVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}