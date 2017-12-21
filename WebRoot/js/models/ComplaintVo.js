if(typeof ComplaintVo == 'undefined') {
	function ComplaintVo() {
		this.id;
		this.userId;
		this.shopId;
		this.userName;
		this.shopName;
		this.content;
		
	}
}

ComplaintVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}