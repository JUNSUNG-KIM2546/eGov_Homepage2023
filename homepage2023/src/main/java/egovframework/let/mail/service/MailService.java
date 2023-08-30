package egovframework.let.mail.service;

import java.util.Properties;

import javax.mail.Session;

public interface MailService {		
	
	//메일session값 셋팅(javax.mail.Session)
	Session mailSetting(Properties props) throws Exception;
	
	//메일보내기
	void sendMail (Session session, String title, String content, String receiver) throws Exception;
}	
