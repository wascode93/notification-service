package com.swvl.challenge.notification.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "push_notification")
@PrimaryKeyJoinColumn(name = "id")
public class PushNotification extends Notification {

  public PushNotification() {}

  public PushNotification(Long userId, String message, Boolean sent) {
    super(userId, message, sent);
  }

  private Boolean read = false;

  public void setRead(Boolean read) {
    this.read = read;
  }
}
