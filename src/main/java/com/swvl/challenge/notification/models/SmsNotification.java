package com.swvl.challenge.notification.models;

import javax.persistence.*;

@Entity
@Table(name = "sms_notification")
@PrimaryKeyJoinColumn(name = "id")
public class SmsNotification extends Notification {
  public SmsNotification() {}

  public SmsNotification(Long userId, String message, Boolean sent) {
    super(userId, message, sent);
  }
}
