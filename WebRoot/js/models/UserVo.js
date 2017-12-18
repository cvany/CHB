if(typeof UserVo == 'undefined') {
	function UserVo() {
		this.id;
		this.userName;
		
	}
}

UserVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}