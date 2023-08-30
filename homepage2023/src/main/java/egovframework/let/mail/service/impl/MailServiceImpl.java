package egovframework.let.mail.service.impl;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import egovframework.let.mail.service.MailService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;

@Service("mailService")	//어노테이션  (서비스에 있는건 임플에 있어야 한다.)
public class MailServiceImpl extends EgovAbstractServiceImpl implements MailService {
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	final String encoding = "UTF-8";
	final String port = "465";
	final String smtpHost = "smtp.gmail.com";
	
	
	@Override
	public Session mailSetting(Properties props) throws Exception {
		Session session = null;
		
		try {
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", smtpHost); //* 변수값
			props.put("mail.smtp.port", port); //* 변수값
			props.put("mail.smtp.auth", true); //*
			props.put("mail.smtp.ssl.enable", true); //*
			props.put("mail.smtp.ssl.trust", smtpHost); //* 변수값
			props.put("mail.smtp.starttls.required", true);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.quit-wait", "false");
			props.put("mail.smtp.socketFactory.port", port); // 변수값
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(propertyService.getString("mail.gmailUser"), propertyService.getString("mail.gmailPassword"));
				}
			});
			
		} catch (Exception e) {
			System.out.println("Setting 실패");
		}
		return session;
	}

	//메일보내기
	public void sendMail(Session session, String title, String content, String receiver) throws Exception {
		Message msg = new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress(propertyService.getString("mail.gmailUser"), propertyService.getString("mail.gmailUserName"), encoding));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			msg.setSubject(title);
			msg.setContent(content, "text/html; charset=utf-8");
			Transport.send(msg);
			
		} catch (Exception e) {
			System.out.println("send 실패");
		}
	}
}
