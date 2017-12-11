if(typeof GoodsVo == 'undefined') {
	function GoodsVo() {
		this.id;
		
	}
}

GoodsVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}