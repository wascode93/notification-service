package com.swvl.challenge.notification.models;

import javax.persistence.*;

@Entity
@Table(name = "email_notification")
@PrimaryKeyJoinColumn(name = "id")
public class EmailNotification extends Notification {
  public EmailNotification() {}

  public EmailNotification(Long userId, String message, Boolean sent) {
    super(userId, message, sent);
  }
}
