package com.swvl.challenge.notification.schedulers;

import com.swvl.challenge.notification.models.EmailNotification;
import com.swvl.challenge.notification.models.User;
import com.swvl.challenge.notification.providers.EmailNotificationSendStrategy;
import com.swvl.challenge.notification.services.EmailNotificationService;
import com.swvl.challenge.notification.services.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailNotificationScheduler {

  private final int PROVIDER_LIMIT_PER_MINUTE = 30;
  private final EmailNotificationService emailNotificationService;
  private final EmailNotificationSendStrategy emailNotificationSendStrategy;
  private final UserService userService;

  public EmailNotificationScheduler(
          EmailNotificationService emailNotificationService,
          EmailNotificationSendStrategy emailNotificationSendStrategy,
          UserService userService) {
    this.emailNotificationService = emailNotificationService;
    this.emailNotificationSendStrategy = emailNotificationSendStrategy;
    this.userService = userService;
  }

  @Scheduled(fixedRate = 60000)
  public void scheduleNotificationSending() {
    List<EmailNotification> notifications = getNotifications();
    sendNotifications(notifications);
    updateNotifications(notifications);
  }

  private List<EmailNotification> getNotifications() {
    return emailNotificationService.getAllPendingNotificationsByLimit(
            PROVIDER_LIMIT_PER_MINUTE);
  }

  private void sendNotifications(List<EmailNotification> notifications) {
    for (EmailNotification notification : notifications) {
      User user = userService.findUserById(notification.getUserId());
      emailNotificationSendStrategy.send(user.getEmail(), notification.getMessage());
      notification.setSent(true);
    }
  }

  private void updateNotifications(List<EmailNotification> notifications) {
    emailNotificationService.updateMultipleNotifications(notifications);
  }
}
