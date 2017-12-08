if(typeof OrderVo == 'undefined') {
	function OrderVo() {
		this.status;
	}
}

OrderVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}