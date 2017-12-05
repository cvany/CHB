package com.chb.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chb.constant.ResultCode;
import com.chb.entity.ResultMessage;
import com.chb.utils.JsonUtil;

/**
 * 登录拦截器
 * @author shilim
 *
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestURI = request.getRequestURI();  
		if(requestURI.indexOf(".do")>0 && requestURI.indexOf("login")<=0){  
			HttpSession session = request.getSession();  
			String username = (String) session.getAttribute("adminUserName");
			if(username!=null){ 
				System.out.println("已登录");
				return true;  
			}else{ 
				response.setContentType("text/html;charset=UTF8");
				PrintWriter out = response.getWriter();
				out.write(JsonUtil.objectToJson(new ResultMessage(false, ResultCode.NO_LOGIN, "没有登录", null)));
				out.flush();
				out.close();
				return false;  
			}  
		}else{  
			return true;  
		}
	}

}
