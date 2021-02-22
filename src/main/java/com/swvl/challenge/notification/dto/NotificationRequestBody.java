package com.swvl.challenge.notification.dto;

public class NotificationRequestBody {
  private Long userId;
  private String message;

  public NotificationRequestBody(Long userId, String message) {
    this.userId = userId;
    this.message = message;
  }

  public Long getUserId() {
    return userId;
  }

  public String getMessage() {
    return message;
  }
}
