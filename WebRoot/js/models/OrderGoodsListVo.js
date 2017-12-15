if(typeof OrderGoodsList == 'undefined') {
	function OrderGoodsList() {
        this.id;
        this.orderId;
        this.goodsId;
        this.count;
        this.sumMoney;
        this.createTime;
        this.order;
        this.goods;
	}
}

OrderGoodsList.prototype.voToJson = function() {
	return JSON.stringify(this);
}