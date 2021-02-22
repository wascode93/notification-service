package com.swvl.challenge.notification.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSendStrategy implements INotificationSendStrategy {

  private final Logger logger = LoggerFactory.getLogger(PushNotificationSendStrategy.class);
  private final JavaMailSender mailSender;

  public EmailNotificationSendStrategy(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void send(String to, String message) {
    logger.info("Sending Email to: " + to + " with message: " + message);
  }

  private void realProviderSend(String to, String message) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(to);
    mailMessage.setText(message);
    mailSender.send(mailMessage);
  }
}
