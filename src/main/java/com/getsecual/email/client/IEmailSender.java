package com.getsecual.email.client;

import com.getsecual.email.client.exception.EmailException;

public interface IEmailSender {

	void sendEmail(Email email) throws EmailException;

}