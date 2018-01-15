package cc.momas.demo;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {
	public static void main(String[] args) throws MessagingException {
		//设置发件人信息
		String userName = "sothere";
		String userMail = "sothere@qq.com";
		String userPwd = "password";
		
		
		Properties props = new Properties();
		//开启debug调试
		props.setProperty("mail.debug", "true");
		//发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		//设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.163.com");
		//发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");
		
		//设置环境信息
		Session session =Session.getInstance(props);
		
		//创建邮件对象
		Message msg = new MimeMessage(session);
		msg.setSubject("JavaMail测试");
		msg.setText("这是一封由JavaMail发送的邮件");
		//设置发件人
		msg.setFrom(new InternetAddress(userMail));
		
		//收件人地址
		Address[] addrs = new Address [1];
		addrs[0] = new InternetAddress("sothere@qq.com");
		
		Transport transport = session.getTransport();
		//连接邮件服务器
		//这一步原本是邮箱的用户名和密码,后来的邮箱服务器都使用了授权码而非普通密码,具体请查询邮箱服务器提供商
		transport.connect(userName,userPwd);

		//发送邮件
		Transport.send(msg,addrs);
		// 关闭链接
		transport.close();
		
	}
}
