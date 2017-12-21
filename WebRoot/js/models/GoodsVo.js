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
        this.goodsPoint;
        this.classifyGoodsId;
        this.goodsNumber;
        this.goodsRemain;
	}
}

GoodsVo.prototype.voToJson = function() {
	return JSON.stringify(this);
}