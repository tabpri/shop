package com.getsecual.email.client.sendgrid;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.getsecual.email.client.Email;
import com.getsecual.email.client.EmailAPICredentials;
import com.getsecual.email.client.IEmailSender;
import com.getsecual.email.client.exception.EmailException;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Component
public class SendGridEmailSender implements IEmailSender {

	@Autowired
	private EmailAPICredentials apiCredentials;
	
	private static final Logger logger = LoggerFactory.getLogger(SendGridEmailSender.class);
	
	@Override
	public void sendEmail(Email email) throws EmailException {

		SendGrid sendgrid = new SendGrid(apiCredentials.getApiKey());
		SendGrid.Email sendGridEmail = new SendGrid.Email();
		sendGridEmail.setFrom(email.getFrom());
		sendGridEmail.setSubject(email.getSubject());

		if ( CollectionUtils.isNotEmpty(email.getTo()) ) {
			sendGridEmail.addTo(email.getTo().toArray(new String[0]));
		}
		if ( CollectionUtils.isNotEmpty(email.getCc() )) {
			sendGridEmail.addCc(email.getCc().toArray(new String[0]));
		}
		if ( CollectionUtils.isNotEmpty(email.getBcc()) ) {
			sendGridEmail.addBcc(email.getBcc().toArray(new String[0]));
		}		
		//sendGridEmail.setHtml(email.getHtmlText());
		sendGridEmail.setText(email.getText());
		try {
			SendGrid.Response response = sendgrid.send(sendGridEmail);
			boolean status = response.getStatus();
			if ( !status ) {
				int code = response.getCode();				
				logger.error("sending email failed with the status" + code);;				
				throw new EmailException(code);
			}
		} catch (SendGridException se) {
			logger.error("sending email failed with the error",se);;
			throw new EmailException(se.getMessage(), se);
		}
	}

}