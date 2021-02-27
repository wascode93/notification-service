package com.swvl.challenge.notification.schedulers;

import com.swvl.challenge.notification.models.EmailNotification;
import com.swvl.challenge.notification.models.User;
import com.swvl.challenge.notification.providers.INotificationSendStrategy;
import com.swvl.challenge.notification.services.EmailNotificationService;
import com.swvl.challenge.notification.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailNotificationScheduler {

  private final int PROVIDER_LIMIT_PER_MINUTE = 30;
  private final EmailNotificationService emailNotificationService;
  private final INotificationSendStrategy notificationSendStrategy;
  private final UserService userService;

  public EmailNotificationScheduler(
          EmailNotificationService emailNotificationService,
          @Qualifier("emailNotificationSendStrategy") INotificationSendStrategy notificationSendStrategy,
          UserService userService) {
    this.emailNotificationService = emailNotificationService;
    this.notificationSendStrategy = notificationSendStrategy;
    this.userService = userService;
  }

  @Scheduled(fixedRate = 60000)
  public void scheduleNotificationSending() {
    List<EmailNotification> notifications = getNotifications();
    sendNotifications(notifications);
    updateNotifications(notifications);
  }

  private List<EmailNotification> getNotifications() {
    return emailNotificationService.getAllPendingNotificationsByLimit(PROVIDER_LIMIT_PER_MINUTE);
  }

  private void sendNotifications(List<EmailNotification> notifications) {
    for (EmailNotification notification : notifications) {
      User user = userService.findUserById(notification.getUserId());
      notificationSendStrategy.send(user.getEmail(), notification.getMessage());
      notification.setSent(true);
    }
  }

  private void updateNotifications(List<EmailNotification> notifications) {
    emailNotificationService.updateMultipleNotifications(notifications);
  }
}
