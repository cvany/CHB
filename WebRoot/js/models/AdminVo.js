if(typeof AdminVo == 'undefined') {
	function AdminVo() {
		this.id;
		this.userName;
		this.password;
	}
}

AdminVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}
