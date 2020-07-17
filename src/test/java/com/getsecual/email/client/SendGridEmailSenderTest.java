package com.getsecual.email.client;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.getsecual.email.client.exception.EmailException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml"})
public class SendGridEmailSenderTest {

	@Autowired
	IEmailSender emailSender;
	
	@Test
	public void testEmailSender() {
		// change the to email address
		Email email=new Email("test","test@secual.com","test2@secual.com","Hi");
		try {
			emailSender.sendEmail(email);
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("error sending email "+e.getStatusCode());
			fail();
		}
	}	
}