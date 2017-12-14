package com.chb.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chb.constant.ResultCode;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;
import com.chb.utils.JsonUtil;

/**
 * 登录拦截器
 * 
 * @author shilim
 *
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		response.addHeader("Access-Control-Allow-Origin","*");
		String requestURI = request.getRequestURI();
		System.out.println(requestURI+"###########");
		if (requestURI.indexOf("business") > 0 && requestURI.indexOf(".do") > 0 && requestURI.indexOf("login") <= 0) {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("businessmanName");
			if (username != null) {
				System.out.println("已登录");
				return true;
			} else {
				response.setContentType("text/html;charset=UTF8");
				PrintWriter out = response.getWriter();
				out.write(JsonUtil.objectToJson(new ResultMessage(false, ResultCode.NO_LOGIN, "没有登录", null)));
				out.flush();
				out.close();
				return false;
			}
		}else if(requestURI.indexOf("isUserLogin.do")>0){	//判断用户是否登录
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			response.setContentType("text/html;charset=UTF8");
			PrintWriter out = response.getWriter();
			if(user!=null){	
				out.write("1");//已经登录，返回状态1
			}else{
				out.write("0");//没有登录，返回状态0
			}
			return false;	//总是返回false，不放行，因为没有该isUserLogin.do路径
		}else {
			return true;
		}
	}

}
