package com.swvl.challenge.notification.schedulers;

import com.swvl.challenge.notification.models.Notification;
import com.swvl.challenge.notification.models.PushNotification;
import com.swvl.challenge.notification.models.User;
import com.swvl.challenge.notification.providers.INotificationSendStrategy;
import com.swvl.challenge.notification.services.PushNotificationService;
import com.swvl.challenge.notification.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PushNotificationScheduler {

  private final int PROVIDER_LIMIT_PER_MINUTE = 30;
  private final PushNotificationService pushNotificationService;
  private final INotificationSendStrategy notificationSendStrategy;
  private final UserService userService;

  public PushNotificationScheduler(
      PushNotificationService pushNotificationService,
      @Qualifier("pushNotificationSendStrategy") INotificationSendStrategy notificationSendStrategy,
      UserService userService) {
    this.pushNotificationService = pushNotificationService;
    this.notificationSendStrategy = notificationSendStrategy;
    this.userService = userService;
  }

  @Scheduled(fixedRate = 60000)
  private void scheduleNotificationSending() {
    List<PushNotification> notifications = getNotifications();
    sendNotifications(notifications);
    updateNotifications(notifications);
  }

  private List<PushNotification> getNotifications() {
    return pushNotificationService.getAllPendingNotificationsByLimit(PROVIDER_LIMIT_PER_MINUTE);
  }

  private void sendNotifications(List<PushNotification> notifications) {
    for (Notification notification : notifications) {
      User user = userService.findUserById(notification.getUserId());
      notificationSendStrategy.send(user.getToken(), notification.getMessage());
      notification.setSent(true);
    }
  }

  private void updateNotifications(List<PushNotification> notifications) {
    pushNotificationService.updateMultipleNotifications(notifications);
  }
}
