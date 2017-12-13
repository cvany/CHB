package com.chb.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.Multipart;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chb.entity.Order;
import com.chb.entity.OrderUVo;
import com.chb.entity.User;
import com.chb.service.OrderServiceU;
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
	
	@Autowired
	private OrderServiceU OrderServiceU;
		
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
					System.out.println("已发送");

				}
			}			
		}				
	}
	
	@RequestMapping("passLogin")           //账号密码登陆
	public void passLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user=userService.findByUsername(username);
		
		if(user!=null) {                                  //用户不为空
			int status=user.getStatus();
			int loginCount=user.getLoginCount();
			                                             
			if(user.getPassword().equals(password)) {       //密码正确
				if(status==0) {                              //账号被冻结
					response.getWriter().write("3");
				}else {
					response.getWriter().write("1");         //登陆成功
					user.setLoginCount(loginCount+1);
					userService.updateUser(user);
					session.setAttribute("user", user);
				}

			}else {                                        //密码不正确
				response.getWriter().write("2");
			}
			
			
		}else {                                             //用户为空
			response.getWriter().write("0");               //0  用户名不存在
		}
	}
	
	@RequestMapping("addLoginCount.do") //增加登陆次数
	public void addLoginCount(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String phone=request.getParameter("phone");
		User user=userService.findByUserPhone(phone);
		
		int count=user.getLoginCount();
		user.setLoginCount(count+1);
		session.setAttribute("user", user);
		userService.updateUser(user);
	}
	
	
/*	@RequestMapping("phoneLogin.do")
	public void phoneLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		String phone=request.getParameter("phone");
		
		User user=userService.findByUsername(username);
		
		if(user!=null) {
			int status =user.getStatus();
			if(status==0) {                       //账号被冻结
				response.getWriter().write("1");
			}else {                                //账号可用
				if(user.getPhone().equals(phone)) {     //账号和手机匹配，开始发送
					
				}else {                          //账号密码不匹配
					response.getWriter().write("2");
				}
			}
			
		}else {                                   //没有此账号
			response.getWriter().write("0");
		}
	}*/
	
	
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

			}
		}
	}
	
	@RequestMapping("insertUser.do")               //插入用户
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
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //设置日期格式
		Date now = df.parse(df.format(new Date()));  //df.format(new Date())为字符串，parse将字符串转换成Date类型
		
		newUser.setRegisterTime(now);
		userService.insertUser(newUser);
		
		response.getWriter().write("0");
	}
	
	
	@RequestMapping("getSession.do")	           //获取当前session
	public @ResponseBody User getSession(HttpSession session) {

		return (User) session.getAttribute("user");
	}
	
	@RequestMapping("updatePass.do")                     //修改密码
	public void updatePass(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		User user=(User) session.getAttribute("user");
		String newPass=request.getParameter("newPass");
		user.setPassword(newPass);
		userService.updateUser(user);
		response.getWriter().write("1");
		
	}

    @RequestMapping("uploadPhoto.do")                   //上传头像 
    @ResponseBody  
    public String test(MultipartFile faviCon,HttpServletResponse response,HttpServletRequest request ) throws IllegalStateException, IOException{
    	
       if(faviCon!=null) {                                      //如果接收的文件不为空
    	   String originalName=faviCon.getOriginalFilename();   //取得接收文件的原名
    	   String fileType=faviCon.getContentType();            //获取文件类型

    	   if(fileType.indexOf("image")<0) {                   //如果文件不是图片
        	   return "0";                                      
           }else {                                              //文件是图片类型
        	   String path="D:\\upload\\";                      //tomcat创建的虚拟目录地址对应的物理地址
        	   File newFile = new File(path+originalName);
        	   faviCon.transferTo(newFile);                     //把文件写入路径  
        	   return originalName;
           }   	                                      	   
   	                                               
       }       
	           return "1";                                     //不作处理                         
    }
    
    @RequestMapping("updatePhoto.do")                            //保存头像
    @ResponseBody  
    public String updatePhoto(String photoPath, String username){
	    	
    	User user=userService.findByUsername(username);
    	user.setPhoto(photoPath);
    	userService.updateUser(user);
		return "1";
    	            
    }
    
    @RequestMapping("getOrderCount.do")                          //获取订单总数
    @ResponseBody  
    public int getOrderCount(HttpSession session){
    	int userid=((User)session.getAttribute("user")).getId();
    	int orderCount = OrderServiceU.getAllOrderById(userid).size();    	

		return orderCount;	            
    }
    
    @RequestMapping("getCurrentPageOrderList.do")                 //获取当前页的订单数据
    @ResponseBody  
    public List<Order> getCurrentPageOrderList(HttpSession session,int currentPage,int totalPage,int onePageOrdersCount){
		int userid=((User) session.getAttribute("user")).getId();
		
    	List<Order> allOrder = OrderServiceU.getAllOrderById(userid);
    	List<Order> currentPageOrder;
    	if(currentPage==totalPage) {
    		currentPageOrder=allOrder.subList(onePageOrdersCount*(currentPage-1), allOrder.size());
    	}else {
    		currentPageOrder=allOrder.subList(onePageOrdersCount*(currentPage-1), onePageOrdersCount*(currentPage));
    	}
		
		return currentPageOrder;	            
    }
    
    @RequestMapping("getWaitPayData.do")                 //获取待付款当前订单数据
    @ResponseBody
    public List<Order> getWaitPayData(HttpSession session,int onePageOrdersCount,int currentPage,int totalPage){

    	int userid=((User) session.getAttribute("user")).getId();
    	    	
    	Order order =new Order();
    	order.setId(userid);
    	order.setStatus(0);
    	
    	OrderUVo orderUVo = new OrderUVo();             //Order包装类
    	orderUVo.setOrder(order);
    	
    	List<Order> WaitPayOrder=OrderServiceU.getOrderStatusById(orderUVo);
    	int orderSize=WaitPayOrder.size();              //订单长度
    	
    	List<Order> currentPageOrder;
    	if(onePageOrdersCount>orderSize&&currentPage==1) {                                //说明长度一页都不满
    		currentPageOrder=WaitPayOrder;
    	}else if(currentPage*onePageOrdersCount-orderSize>onePageOrdersCount) //说明该页为空白页
    	{      
    		return null;
    	}else if(currentPage*onePageOrdersCount>orderSize)                     //说明长度一页以上，且当前页未满
    	{
    		currentPageOrder=WaitPayOrder.subList(onePageOrdersCount*(currentPage-1), orderSize);
    	}else {                                                                //当前页已满
    		currentPageOrder=WaitPayOrder.subList(onePageOrdersCount*(currentPage-1), onePageOrdersCount*(currentPage));
    	}
		   	
		return currentPageOrder;		   	
    }
    
    @RequestMapping("getWaitFaHuoData.do")                 //获取待发货当前的订单数据
    @ResponseBody
    public List<Order> getWaitFaHuoData(HttpSession session,int onePageOrdersCount,int currentPage,int totalPage){
    	int userid=((User) session.getAttribute("user")).getId();
    	
    	Order order =new Order();
    	order.setId(userid);
    	order.setStatus(2);
    	
    	OrderUVo orderUVo = new OrderUVo();             //Order包装类
    	orderUVo.setOrder(order);
    	
    	List<Order> WaitFaHuo=OrderServiceU.getOrderStatusById(orderUVo);
    	List<Order> currentPageOrder;
    	int orderSize=WaitFaHuo.size();
    	
    	if(onePageOrdersCount>orderSize&&currentPage==1) {                                //说明长度一页都不满
    		currentPageOrder=WaitFaHuo;
    	}else if(currentPage*onePageOrdersCount-orderSize>onePageOrdersCount) //说明该页为空白页
    	{      
    		return null;
    	}else if(currentPage*onePageOrdersCount>orderSize)                     //说明长度一页以上，且当前页未满
    	{
    		currentPageOrder=WaitFaHuo.subList(onePageOrdersCount*(currentPage-1), orderSize);
    	}else {                                                                //当前页已满
    		currentPageOrder=WaitFaHuo.subList(onePageOrdersCount*(currentPage-1), onePageOrdersCount*(currentPage));
    	}
		   	
		return currentPageOrder;   	
    }
    
    @RequestMapping("getWaitShouHuoData.do")                 //获取待收货当前页的订单数据
    @ResponseBody
    public List<Order> getWaitShouHuoData(HttpSession session,int onePageOrdersCount,int currentPage,int totalPage){
    	int userid=((User) session.getAttribute("user")).getId();
    	
    	Order order =new Order();
    	order.setId(userid);
    	order.setStatus(4);
    	
    	OrderUVo orderUVo = new OrderUVo();             //Order包装类
    	orderUVo.setOrder(order);
    	
    	List<Order> WaitShouHuo=OrderServiceU.getOrderStatusById(orderUVo);
    	List<Order> currentPageOrder;
    	int orderSize=WaitShouHuo.size();
    	
    	if(onePageOrdersCount>orderSize&&currentPage==1) {                                //说明长度一页都不满
    		currentPageOrder=WaitShouHuo;
    	}else if(currentPage*onePageOrdersCount-orderSize>onePageOrdersCount) //说明该页为空白页
    	{      
    		return null;
    	}else if(currentPage*onePageOrdersCount>orderSize)                     //说明长度一页以上，且当前页未满
    	{
    		currentPageOrder=WaitShouHuo.subList(onePageOrdersCount*(currentPage-1), orderSize);
    	}else {                                                                //当前页已满
    		currentPageOrder=WaitShouHuo.subList(onePageOrdersCount*(currentPage-1), onePageOrdersCount*(currentPage));
    	}
    	
		return currentPageOrder;   	
    }
    
    @RequestMapping("getWaitPingJiaData.do")                 //获取待评价当前页的订单数据
    @ResponseBody
    public List<Order> getWaitPingJiaData(HttpSession session,int onePageOrdersCount,int currentPage,int totalPage){
    	int userid=((User) session.getAttribute("user")).getId();
    	
    	Order order =new Order();
    	order.setId(userid);
    	order.setStatus(5);
    	
    	OrderUVo orderUVo = new OrderUVo();             //Order包装类
    	orderUVo.setOrder(order);
    	
    	List<Order> WaitPingJia=OrderServiceU.getOrderStatusById(orderUVo);
    	List<Order> currentPageOrder;
    	int orderSize=WaitPingJia.size();
    	
    	if(onePageOrdersCount>orderSize&&currentPage==1) {                                //说明长度一页都不满
    		currentPageOrder=WaitPingJia;
    	}else if(currentPage*onePageOrdersCount-orderSize>onePageOrdersCount) //说明该页为空白页
    	{      
    		return null;
    	}else if(currentPage*onePageOrdersCount>orderSize)                     //说明长度一页以上，且当前页未满
    	{
    		currentPageOrder=WaitPingJia.subList(onePageOrdersCount*(currentPage-1), orderSize);
    	}else {                                                                //当前页已满
    		currentPageOrder=WaitPingJia.subList(onePageOrdersCount*(currentPage-1), onePageOrdersCount*(currentPage));
    	}
    	
		return currentPageOrder;    	
    }
    
    @RequestMapping("getTuihuoData.do")                 //获取退单当前页的订单数据
    @ResponseBody
    public List<Order> getTuihuoData(HttpSession session,int onePageOrdersCount,int currentPage,int totalPage){
    	int userid=((User) session.getAttribute("user")).getId();
    	
    	Order order =new Order();
    	order.setId(userid);
    	order.setStatus(-1);
    	
    	OrderUVo orderUVo = new OrderUVo();             //Order包装类
    	orderUVo.setOrder(order);
    	
    	List<Order> TuiHuo=OrderServiceU.getOrderStatusById(orderUVo);
    	List<Order> currentPageOrder;
    	int orderSize=TuiHuo.size();
    	
    	if(onePageOrdersCount>orderSize&&currentPage==1) {                                //说明长度一页都不满
    		currentPageOrder=TuiHuo;
    	}else if(currentPage*onePageOrdersCount-orderSize>onePageOrdersCount) //说明该页为空白页
    	{      
    		return null;
    	}else if(currentPage*onePageOrdersCount>orderSize)                     //说明长度一页以上，且当前页未满
    	{
    		currentPageOrder=TuiHuo.subList(onePageOrdersCount*(currentPage-1), orderSize);
    	}else {                                                                //当前页已满
    		currentPageOrder=TuiHuo.subList(onePageOrdersCount*(currentPage-1), onePageOrdersCount*(currentPage));
    	}
    	
		return currentPageOrder;    	
    }
	

}
