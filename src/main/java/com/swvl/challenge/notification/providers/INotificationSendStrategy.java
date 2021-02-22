package com.swvl.challenge.notification.providers;

public interface INotificationSendStrategy {

  void send(String to, String message);
}
