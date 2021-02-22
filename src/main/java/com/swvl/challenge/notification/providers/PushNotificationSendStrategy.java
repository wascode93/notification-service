package com.swvl.challenge.notification.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationSendStrategy implements INotificationSendStrategy {

  private final Logger logger = LoggerFactory.getLogger(PushNotificationSendStrategy.class);

  @Override
  public void send(String to, String message) {
    logger.info("Sending Push to: " + to + " with message: " + message);
  }
}
