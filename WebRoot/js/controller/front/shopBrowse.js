/**
 * 浏览商店页面JS 崔文元 2017年12月7日
 */
$(function() {//页面初始化触发函数
	// 定位地址
	var value = decodeURIComponent(getParam("loc"));
	var lng = getParam("lng");
	var lat = getParam("lat");
	$("#location").text(value);
	//将数据存储在sessionStorage
	sessionStorage.setItem("lng",lng);
	sessionStorage.setItem("lat",lat);
	sessionStorage.setItem("loc",value);
	//对搜索输入框进行监听
	$("input[name='search']").keydown(function(e){
        if(e.keyCode ==13){
        	var value =$("input[name='search']").val();
        	if(value==""){
        		alert("输入内容不能为空哦！");
        		return false;
        	}
        	//否则向后台请求数据
        	$.ajax({
        		url : rootPath+"/findShopByKeyWords.do",
        		data:"shopName="+value,
        		dataType:"json",
        		success : function(data) {
        			produceShop(data);
        		}
        	});
        	
            return false;//触发键盘事件enter 防止冒泡产生
        }
    });
	// 注意：导航 依赖 element 模块，否则无法进行功能性操作
//	layui.use('element', function() {
//		var element = layui.element;
//	});
	
	// 请求商店分类
	$.ajax({
		url : rootPath+"/findAllShopClassify.do",
		dataType:"json",
		success : function(data) {
			for(var i=0;i<data.length;i++){
				$("#shopClassifyDiv")
				.append("<span onclick='findShopByShopClassifyId("+data[i].id+")'>"+data[i].name+"</span>");
			}
		}
	});
	//获取第一页商店展示信息
	getAllShopByPage(0);
})

//跳转个人中心页面
function myOrder() {
	isUserLogin2("user/userLogin.html","user/userPersonal.html");
}

//根据页码来获取商店展示信息
function getAllShopByPage(pageNum){
	//分页请求所有商店和商品，每页9条记录
	$.ajax({
		url : rootPath+"/findShopAndGoodsByPage.do",
		data:"startColum="+pageNum,
		dataType:"json",
		success : function(data) {
			produceShop(data);
		}
	});
}
//生成商店展示信息函数，内部调用
function produceShop(data){
	$(".shopList").empty();
	for(var i=0;i<data.length;i++){
		$(".shopList").append("<div onclick='findShopById("+data[i].shopId+")' class='shop'><div><div style='float: left;'><img width='80px' src='../../img/yp.png'></div><div style='padding-left: 100px'><p><h3><b>"+data[i].shopName+"</b></h3></p><p>★★★☆☆ 月售"+data[i].monthSales+"单</p><p>￥"+data[i].startPrice+"起送</p></div></div><div></div><div class='notice'><span><img width='20px' src='../../img/notice.png' /></span>&nbsp;&nbsp;"+data[i].shopNotice+" </div><div><div class='food'><div><img width='100px' src='../../img/yp2.png'></div><div class='goodsNam'>"+data[i].goodsName1+"</div><div class='price'>￥"+data[i].price1+"</div></div><div class='food'><div><img width='100px' src='../../img/yp2.png'></div><div  class='goodsNam'>"+data[i].goodsName2+"</div><div class='price'>￥"+data[i].price2+"</div></div><div class='food'><div><img width='100px' src='../../img/yp2.png'></div><div  class='goodsNam'>"+data[i].goodsName3+"</div><div class='price'>￥"+data[i].price3+"</div></div></div></div>");
	}
}

//根据商家id跳转到商家页面
function findShopById(id) {
	window.location.href="business/shop.html?id="+id;
}

//根据商店分类id查找相对应商店
function findShopByShopClassifyId(id){
	if (0==id) {	//获取全部商家展示信息，其实也是第一页
		getAllShopByPage(0);
	}
	else{	//根据商店分类id获取商店展示信息
		//分页请求所有商店和商品，每页9条记录
		$.ajax({
			url : rootPath+"/findShopByShopClassifyId.do",
			data: {"id":id},
			dataType:"json",
			success : function(data) {
				produceShop(data);
			}
		});
	}
}