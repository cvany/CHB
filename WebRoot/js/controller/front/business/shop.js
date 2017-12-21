/**
 * 商家展示页面JS
 * 崔文元
 * 2017年12月11日
 */
var id = 0;
$(function () {
	var curUrl = window.document.location.href;
	sessionStorage.setItem("curUrl",curUrl);
	//获取用户登录标识
	var userToken =sessionStorage.getItem("userToken");
	if(userToken!="null"&&userToken=="true"){
		var userName =sessionStorage.getItem("userName");
		$(".login").remove();
		$(".register").remove();
		$(".layui-nav").append("<li class='layui-nav-item alreadyLogin' style='float: right;'><a href='javascript:;' onclick='logout()'>退出</a></li>");
		$(".layui-nav").append("<li class='layui-nav-item alreadyLogin' style='float: right;'><a href='../user/userPersonal.html'>"+userName+"</a></li>");
	}
    var loc = sessionStorage.getItem("loc");
    id = getParam("id");
    //请求商家信息
    getShopInfo(id);
    //请求所有商品信息
    getAllGoods(id);
    //绑定所有商品按钮点击事件
    $(".all-goods").bind("click", getAllGoods);
    //绑定全部评价按钮点击事件
    $(".all-comments").bind("click", getAllComments);
    //绑定清空按钮事件
    $(".headche span").bind("click", clearChe);
    //绑定搜索按钮事件
    $("input[name='search']").keydown(function (e) {
        if (e.keyCode == 13) {
            var value = $("input[name='search']").val();
            if (value == "") {
                alert("输入内容不能为空哦！");
                return false;
            }
            //否则向后台请求数据
            $.ajax({
                url: rootPath + "/findGoodsByKeywords.do",
                data: {"goodsName": value, "id": id},
                dataType: "json",
                success: function (data) {
                    $(".goodsList").empty();
                    for (var i = 0; i < data.length; i++) {
                        $(".goodsList").append("<div class='goods'>	<div style='float: left;'><img width='80px' src='../../../img/yp2.png' />	</div>	<div class='ginfo' style='float: left;'><p><h4><b><span class='spmc'>" + data[i].goodsName + "</span></b></h4></p><p>★★★☆☆ 月售" + data[i].goodsSales + "单</p><p style='color: red;'><b>￥<span class='jg'>" + data[i].goodsPrice + "</span></b></p></div>	<div id='div" + data[i].id + "' class='chebtn'><button onclick='addGoodsChe(this," + data[i].id + ")' class='layui-btn layui-btn-sm layui-btn-radius layui-btn-normal'>加入购物车</button></div></div>");
                    }
                }
            });
            return false;//触发键盘事件enter 防止冒泡产生
        }
    });
});

/**
 * 退出登录
 * @returns
 */
function logout(){
	$.ajax({
		url : rootPath+"/logout.do",
		dataType:"json",
		success : function(data) {
			sessionStorage.setItem("userToken","false");
			$(".alreadyLogin").remove();
			$(".layui-nav").append("<li class='layui-nav-item login' style='float: right;'><a href='../user/userLogin.html'>登录</a></li>");
			$(".layui-nav").append("<li class='layui-nav-item register' style='float: right;'><a href='../user/register.html'>注册</a></li>");
		}
	});
}
//跳转个人中心页面
function myOrder() {
	isUserLogin("../user/userLogin.html");
	//保存当前的URL地址
	var curUrl = window.document.location.href;
	sessionStorage.setItem("curUrl",curUrl);
	window.location.href ="../user/userPersonal.html";
}

var shopInfo = new ShopVo();

//根据id获取商店信息
function getShopInfo() {
    $.ajax({
        url: rootPath + "/findShopInfoById.do",
        data: {"id": id},
        dataType: "json",
        success: function (data) {
            shopInfo.id = data.id;
            shopInfo.shopName = data.shopName;
            $("#shopNameSpan").text(data.shopName);
            $("#mouthSalesSpan").text(data.monthSales);
            $("#averangeTimeSpan").text(data.averangeTime);
            $("#startPriceSpan").text(data.startPrice);
            $(".noticeContent").text(data.shopNotice);
        }
    });
}

var goodsList = {}

//根据商店id获取所有商品信息
function getAllGoods() {
    $.ajax({
        url: rootPath + "/findAllGoodsByShopId.do",
        data: {"id": id},
        dataType: "json",
        success: function (data) {
            $(".goodsList").empty();
            for (var i = 0; i < data.length; i++) {
                goodsList[data[i].id] = data[i]
                $(".goodsList").append("<div class='goods'>	<div style='float: left;'><img width='80px' src='" + "/../" + data[i].goodsPhoto + "' />	</div>	<div class='ginfo' style='float: left;'><p><h4><b><span class='spmc'>" + data[i].goodsName + "</span></b></h4></p><p>★★★☆☆ 月售" + data[i].goodsSales + "单</p><p style='color: red;'><b>￥<span class='jg'>" + data[i].goodsPrice + "</span></b></p></div>	<div id='div" + data[i].id + "' class='chebtn'><button onclick='addGoodsChe(this," + data[i].id + ")' class='layui-btn layui-btn-sm layui-btn-radius layui-btn-normal'>加入购物车</button></div></div>");
            }
        }
    });
}

//根据商店id获取所有评价
function getAllComments() {
    $(".goodsList").empty();
    alert(id);
}

var shopCartList = {}

//加商品入购物车
function addGoodsChe(obj, goodsId) {
    var orderGoodsList = new OrderGoodsList();
    orderGoodsList.goodsId = goodsId;
    orderGoodsList.count = 1;
    orderGoodsList.goods = goodsList[goodsId]
    orderGoodsList.sumMoney = orderGoodsList.count * orderGoodsList.goods.goodsPrice
    shopCartList[goodsId] = orderGoodsList
    //获取父元素并将按钮置为不可用状态
    var divobj = $(obj).parent(".chebtn");
    divobj.html("<button class='layui-btn layui-btn-sm layui-btn-radius layui-btn-disabled'>已加入购物车</button>");
    //获取商品名称
    var spmc = divobj.prev().find(".spmc").text();
    //获取商品价格
    var jg = divobj.prev().find(".jg").text();
    //添加到购物车
    $(".goodsCheList").append("<div class='goodsChe'><div class='goodsname'>" + spmc + "</div><div class='goodsnum'><span onclick='subGoodsNum(this," + jg + "," + goodsId + ")'>-</span><input onfocus='saveOldValue(this," + jg + ")' onblur='derectUpdateGoodsNum(this," + jg + "," + goodsId + ")' style='width: 20px' type='text' value='1'/><span onclick='addGoodsNum(this," + jg + "," + goodsId + ")'>+</span></div><div class='goodspri'>￥" + jg + "</div></div>");
    //改变金额值
    calSum("add", jg);
}

/**
 * 减少商品数量,参数：对象，该商品的价格
 * @param obj 点击的对象
 * @param jg    该商品的价格
 * @param goodsId    该商品id
 * @returns
 */
function subGoodsNum(obj, jg, goodsId) {
    var value = updateGoodsNum("sub", obj);
    if (value == 0) {
        delete shopCartList[goodsId]
        $(obj).parent(".goodsnum").parent(".goodsChe").remove();
        //将加入购物车按钮修改为可用
        $("#div" + goodsId).html("<button onclick='addGoodsChe(this," + goodsId + ")' class='layui-btn layui-btn-sm layui-btn-radius layui-btn-normal'>加入购物车</button>");
    } else {
        shopCartList[goodsId].count--;
        shopCartList[goodsId].sumMoney = shopCartList[goodsId].count * shopCartList[goodsId].goods.goodsPrice
    }
    calSum("sub", jg);
}

/**
 * 增加商品数量，参数：对象，该商品的价格
 * @param obj
 * @param jg
 * @returns
 */
function addGoodsNum(obj, jg, goodsId) {
    shopCartList[goodsId].count++;
    shopCartList[goodsId].sumMoney = shopCartList[goodsId].count * shopCartList[goodsId].goods.goodsPrice
    updateGoodsNum("add", obj);
    calSum("add", jg);
}

//保存旧值
var oldVal = 0;

function saveOldValue(obj) {
    var temp = $(obj).val();
    if (!isNum(temp)) {
        return;
    } else {
        if (parseInt(temp) < 1)
            return;
    }
    oldVal = temp;
}

/**
 * 直接修改商店数量
 * @returns
 */
function derectUpdateGoodsNum(obj, jg, goodsId) {
    var value = $(obj).val();
    if (!isNum(value)) {
        alert("输入参数不正确！");
    } else {
        if (parseInt(value) >= 1) {
            var off = parseInt(value) - parseInt(oldVal);	//拿到修改后的偏移值
            var newVal = off * parseFloat(jg);
            shopCartList[goodsId].count += newVal;
            shopCartList[goodsId].sumMoney = shopCartList[goodsId].count * shopCartList[goodsId].goods.goodsPrice
            calSum("add", newVal);
        } else {
            alert("参数不能小于1");
        }
    }
}

//清空购物车
function clearChe() {
    shopCartList = {}
    $(".goodsCheList").empty();
    $(".priceSum").text(0);	//设置金额为0
    getAllGoods(id);
}

//下单
function layOrder() {
	isUserLogin("../user/userLogin.html");
    if (Object.keys(shopCartList).length == 0) return
    var order = {
        shopInfo: shopInfo,
        shopCartList: shopCartList
    }
    sessionStorage.order = JSON.stringify(order)
    window.location.href = "../order/order-check.html"
}

/**
 * 内部函数，判断是否为数字
 * @param value
 * @returns
 */
function isNum(value) {
    var patrn = /^(-)?\d+(\.\d+)?$/;
    if (patrn.exec(value) == null || value == "") {
        return false
    } else {
        return true
    }
}

/**
 * 内部函数,修改商品数量函数
 * @param op    表示操作
 * @param obj
 * @returns
 */
function updateGoodsNum(op, obj) {
    //修改input里面的值
    var o = $(obj);
    var value = o.parent(".goodsnum").find("input").val();
    switch (op) {
        case "add":
            value = parseInt(value) + 1;
            break;
        case "sub":
            value = parseInt(value) - 1;
            break;
    }
    o.parent(".goodsnum").find("input").val(value);
    return value;
}

/**
 * 内部函数,计算购物车商品价格
 * @param op 操作
 * @param jg 该商品的价格
 * @returns
 */
function calSum(op, jg) {
    var oldSum = $(".priceSum").text();
    if (oldSum == null || oldSum == "") {
        $(".priceSum").text(jg);
        return;
    }
    switch (op) {
        case "sub":
            var newSum = parseFloat(oldSum) - parseFloat(jg);
            $(".priceSum").text(newSum);
            break;
        case "add":
            var newSum = parseFloat(oldSum) + parseFloat(jg);
            $(".priceSum").text(newSum);
            break;
    }

}