if(typeof ShopVo == 'undefined') {
	function ShopVo() {
		this.id;
		
	}
}

ShopVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}