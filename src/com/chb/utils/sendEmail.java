package com.chb.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendEmail {

	public void send(String receiverEmail,String tempPassword) throws Exception { 

		String myEmailAccount = "707592806@qq.com";  //我的邮箱账号
		String myEmailPassword="oykrpljrszjubfji";    //我的邮箱授权码
		
		String myStmpHost="smtp.qq.com";   //qq stmp服务器
		
		 
		

		
		Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myStmpHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        
        //SSL安全验证
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        
		
		//根据配置创建会话对象		
        Session session = Session.getInstance(props);
        session.setDebug(true);// 设置为debug模式, 可以查看详细的发送 log
		
		//创建邮件
        MimeMessage message = createMessage(session, myEmailAccount, receiverEmail,tempPassword);
		
		
        Transport transport = session.getTransport();   //根据 Session 获取邮件传输对象
        
        transport.connect(myEmailAccount, myEmailPassword);  //使用 邮箱账号 和 授权码 连接邮件服务器

        transport.sendMessage(message, message.getAllRecipients()); //发送邮件, getAllRecipients()发到所有的收件地址


        transport.close();  //关闭连接
		
	}
	
    public static MimeMessage createMessage(Session s,String sendMail, String receiverEmail,String newPass) throws Exception {
         //创建一封邮件
        MimeMessage message = new MimeMessage(s);  //创建邮件

        message.setFrom(new InternetAddress(sendMail, "吃货宝安全中心", "UTF-8"));   //发件人

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiverEmail, "吃货宝用户", "UTF-8"));   //收件人

        message.setSubject("吃货宝密码找回", "UTF-8");              //邮件标题
 
        message.setContent("您好，您的临时密码是"+newPass+"，登陆以后请及时修改密码", "text/html;charset=UTF-8"); //邮件正文

        message.setSentDate(new Date());            //设置邮件发送时间

        message.saveChanges();         //保存设置

        return message;
    }
    
 
    
    

}