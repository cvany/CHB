if(typeof GoodsVo == 'undefined') {
	function GoodsVo() {
        this.id;
        this.shopId;
        this.goodsName;
        this.goodsPrice;
        this.goodsPhoto;
        this.goodsDescription;
        this.goodsSales;
        this.isPass;
        this.isOnline;
        this.classifyGoodsId;
        this.goodsNumber;
	}
}

GoodsVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}