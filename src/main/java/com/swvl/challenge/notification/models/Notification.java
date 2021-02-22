package com.swvl.challenge.notification.models;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  private String message;
  private Boolean sent = false;

  public Notification() {}

  public Notification(Long userId, String message, Boolean sent) {
    this.userId = userId;
    this.message = message;
    this.sent = sent;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setSent(Boolean sent) {
    this.sent = sent;
  }

  public Long getUserId() {
    return userId;
  }

  public String getMessage() {
    return message;
  }

  public Boolean getSent() {
    return sent;
  }
}
