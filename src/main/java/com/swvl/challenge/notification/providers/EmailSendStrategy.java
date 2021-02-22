package com.swvl.challenge.notification.providers;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSendStrategy implements INotificationSendStrategy {

  private final JavaMailSender mailSender;

  public EmailSendStrategy(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void send(String to, String message) {
    //    SimpleMailMessage mailMessage = new SimpleMailMessage();
    //    mailMessage.setTo(to);
    //    mailMessage.setText(message);
    //    mailSender.send(mailMessage);
    System.out.println("Email: " + to + " -> " + message);
  }
}
