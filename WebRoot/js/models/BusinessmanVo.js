if(typeof BusinessmanVo == 'undefined') {
	function BusinessmanVo() {
        this.id;
        this.businessmanName;
        this.salt;
        this.phone;
        this.email;
        this.registerTime;
        this.photo;
        this.accountBalance;
	}
}

BusinessmanVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}