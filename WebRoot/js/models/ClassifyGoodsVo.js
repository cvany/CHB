if(typeof ClassifyGoodsVo == 'undefined') {
	function ClassifyGoodsVo() {
        this.id;
        this.classifyName;
        this.classifyDescription;
        this.shopId;
	}
}

ClassifyGoodsVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}