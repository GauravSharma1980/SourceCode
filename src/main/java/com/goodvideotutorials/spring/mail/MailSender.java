package com.goodvideotutorials.spring.mail;

import javax.mail.MessagingException;

public interface MailSender {

	public abstract void send(String to, String subject, String body) throws MessagingException;

}