if(typeof PageVo == 'undefined') {
	function PageVo() {
		this.pageNum;
		this.pageSize;
		this.order;
		this.keyWords;
		this.fuzzy;
	}
}

PageVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}
