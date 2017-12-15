if(typeof OrderVo == 'undefined') {
	function OrderVo() {
		this.status;
		this.sumMoney;
	}
}

OrderVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}