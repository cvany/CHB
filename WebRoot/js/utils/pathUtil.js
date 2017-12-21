/**
 * 获取项目根路径工具
 */
var rootPath =getRootPath_web();
function getRootPath_web() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}

//根据参数名获取URL后面的参数值
function getParam(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  r[2]; return null;
}

/**
 * 判断用户是否登录
 * @returns
 */
function isUserLogin(loginUrl) {
	$.ajax({
		url : rootPath+"/isUserLogin.do",
		dataType:"json",
		success : function(data) {
			if(data=="0"){	//未登录情况
				window.location.href=loginUrl;
			}
		}
	});
}
/**
 * 判断用户是否登录
 * @param pageUrl 已登录需要跳转页面的URL
 * @returns
 */
function isUserLogin2(loginUrl,desUrl) {
	$.ajax({
		url : rootPath+"/isUserLogin.do",
		dataType:"json",
		success : function(data) {
			if(data=="0"){	//未登录情况
				window.location.href=loginUrl;
			}else{
				window.location.href= desUrl;
			}
		}
	});
}