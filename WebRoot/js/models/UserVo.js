if(typeof UserVo == 'undefined') {
	function UserVo() {
		this.id;
		
	}
}

UserVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}