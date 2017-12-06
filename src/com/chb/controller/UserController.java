package com.chb.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.User;
import com.chb.service.UserService;
import com.chb.utils.*;
/**
 * 用户控制类
 * @author 黄雨晨
 *2017年12月4日
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("findUser")
	@ResponseBody
	public User findUser() throws IOException{
		User user = userService.findByUsername("小花");
		return user;
	}
	

	
	@RequestMapping("sendEmail")
	public void sendEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{

		String username=request.getParameter("username");
		String email=request.getParameter("email");
		
		
		User user=userService.findByUsername(username);
		
		if(user==null) {         //用户为空
			response.getWriter().write("null");
		}else {                    //用户不为空
			int status=user.getStatus();
	
			if(user.getEmail().equals(email)) {    //0  匹配  
				if(status==0) {                   //账号被冻结
					response.getWriter().write("3");  //3   账号被冻结
				}else {                               //发送
					response.getWriter().write("0");  
					String randomPass=String.valueOf((int)((Math.random()*9+1)*100000));  //生成6位随机数
					
					sendEmail send = new sendEmail();
					send.send(email, randomPass);
					
					user.setPassword(randomPass);
					userService.updateUser(user);
					System.out.println(randomPass);
				}

			}else {                               //1 不匹配
				response.getWriter().write("1");
				
			}
		}
			
	}
	
	@RequestMapping("sendCode")                    //手机短信验证登陆
	public void sendCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String phoneNum=request.getParameter("phoneNum");
		User user=userService.findByUserPhone(phoneNum);
		
		if(user==null) {                               //手机用户为空
		   response.getWriter().write("0");
		}else {                                        //手机用户不为空
			int status=user.getStatus();
			if(status==0) {                            //该用户被冻结，无法发送短信
				response.getWriter().write("3");
			}else {
				HttpClientUtil sendCode =HttpClientUtil.getInstance();
				String randomCode=String.valueOf((int)((Math.random()*9+1)*100000));  //生成6位随机数
				
				
				String context="你的验证码是"+randomCode;
				int result=sendCode.sendMsgUtf8(context, phoneNum);
				
				if(result>0) {                    //发送成功
					response.getWriter().write(randomCode);   //1 成功发送验证码

				}
			}
			
		}
		
		
	}
	
	@RequestMapping("passLogin")           //账号密码登陆
	public void passLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user=userService.findByUsername(username);
		
		if(user!=null) {                                  //用户不为空
			int status=user.getStatus();
			                                             
			if(user.getPassword().equals(password)) {       //密码正确
				if(status==0) {                              //账号被冻结
					response.getWriter().write("3");
				}else {
					response.getWriter().write("1");
				}

			}else {                                        //密码不正确
				response.getWriter().write("2");
			}
			
			
		}else {                                             //用户为空
			response.getWriter().write("0");               //0  用户名不存在
		}
	}
	
	
	@RequestMapping("judgeName.do")                  //判断用户名是否被占用
	public void judgeName(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		User user=userService.findByUsername(username);
		
		if(user!=null) {                          //用户名已被占用，不能注册
			response.getWriter().write("0");
		}else {
			response.getWriter().write("1");      //用户名可用
		}
	}
	
	@RequestMapping("judgePhone.do")                     //判断手机是否被注册
	public void judgePhone(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String phone=request.getParameter("phone");
		User user=userService.findByUserPhone(phone);
		
		if(user!=null) {                          //手机已被占用，不能注册
			response.getWriter().write("0");
		}else {
			response.getWriter().write("1");      //手机可用
		}
	}
	
	@RequestMapping("judgeEmail.do")                     //判断手机是否被注册
	public void judgeEmail(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String email=request.getParameter("email");
		User user=userService.findByUserEmail(email);
		
		if(user!=null) {                          //邮箱已被占用，不能注册
			response.getWriter().write("0");
		}else {
			response.getWriter().write("1");      //邮箱可用
		}
	}
	
	@RequestMapping("registerCode.do")                     //发送验证码注册
	public void registerCode(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		
		
		User user=userService.findByUserPhone(phone);
		User user2=userService.findByUserEmail(email);
		User user3=userService.findByUsername(username);
		
		if(user!=null) {                          //0 手机已被注册，不能发验证码
			response.getWriter().write("0");
		}else if(user2!=null){                    //1 email被注册，不能注册
			response.getWriter().write("1");
		}else if(user3!=null){                    //2 用户名被注册，不能注册
			response.getWriter().write("2");
		}else {
			System.out.println("发短信");
			HttpClientUtil sendCode =HttpClientUtil.getInstance();
			String randomCode=String.valueOf((int)((Math.random()*9+1)*100000));  //生成6位随机数
						
			String context="你的验证码是"+randomCode;
			int result=sendCode.sendMsgUtf8(context, phone);
			
			if(result>0) {                    //发送成功
				response.getWriter().write(randomCode);   //1 成功发送验证码

				/*User newUser = new User();
				newUser.setUserName(username);
				newUser.setEmail(email);
				newUser.setPassword(password);
				newUser.setLoginCount(0);
				newUser.setPhone(phone);
				newUser.setSalt("0");
				newUser.setStatus(0);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   //设置日期格式
				Date now = df.parse(df.format(new Date()));  //df.format(new Date())为字符串，parse将字符串转换成Date类型
				
				newUser.setRegisterTime(now);
				userService.insertUser(newUser);*/
			}
		}
	}
	
	@RequestMapping("insertUser.do")
	public void updateStatus(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception  {
		/*String username=request.getParameter("username");*/
		
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		
		User newUser = new User();
		newUser.setUserName(username);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setLoginCount(0);
		newUser.setPhone(phone);
		newUser.setSalt("0");
		newUser.setStatus(1);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   //设置日期格式
		Date now = df.parse(df.format(new Date()));  //df.format(new Date())为字符串，parse将字符串转换成Date类型
		
		newUser.setRegisterTime(now);
		userService.insertUser(newUser);
		
		response.getWriter().write("0");
	}
	
	
	
	

}
