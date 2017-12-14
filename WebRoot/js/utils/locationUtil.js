/**
 * 定位工具
 */
function locationUtil(){
	
	/**
	 * 浏览器定位功能,
	 * param 回调函数
	 */
	this.localCity =function(callback){
		var myCity = new BMap.LocalCity();
		myCity.get(callback);
	}
	
	/**
	 * 根据地名定位
	 * param 地名
	 */
	this.geoByAreaName =function(areaName,callback){
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(areaName, callback, "湛江市");
	}
	
	/**
	 * 关键字输入提示
	 * param 绑定组件的id
	 */
	this.autoCompelete =function(id){
		new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : id
			});
	}
	
}