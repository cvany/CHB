if(typeof ComplaintVo == 'undefined') {
	function ComplaintVo() {
		this.id;
		
	}
}

ComplaintVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}