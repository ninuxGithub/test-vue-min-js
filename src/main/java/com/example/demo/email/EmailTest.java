package com.example.demo.email;

import jodd.mail.Email;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpSslServer;

//发送失败

public class EmailTest {
	public static void main(String[] args) {
		Email email = Email.create();
		email.addMessage(new EmailMessage("Hello", " I am Vincen"));
		email.addText("这里写的是Test");
		// email.embedFile(new File("E:\\我的文档\\My Pictures\\__唯美素材", "psb65.jpg"));
		email.from("xxx").to("xxx");
		email.subject("我的主题我做主");

		SendMailSession mailSession = new SmtpSslServer("smtp.qq.com", "xxx", "xxx").createSession();
		mailSession.open();
		mailSession.sendMail(email);
		mailSession.close();
		System.out.println("发送成功!...");
	}
}
