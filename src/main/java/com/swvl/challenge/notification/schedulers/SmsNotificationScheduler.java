package com.swvl.challenge.notification.schedulers;

import com.swvl.challenge.notification.models.Notification;
import com.swvl.challenge.notification.models.SmsNotification;
import com.swvl.challenge.notification.models.User;
import com.swvl.challenge.notification.providers.INotificationSendStrategy;
import com.swvl.challenge.notification.services.SmsNotificationService;
import com.swvl.challenge.notification.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmsNotificationScheduler {

  private final int PROVIDER_LIMIT_PER_MINUTE = 30;
  private final SmsNotificationService smsNotificationService;
  private final INotificationSendStrategy notificationSendStrategy;
  private final UserService userService;

  public SmsNotificationScheduler(
      SmsNotificationService smsNotificationService,
      @Qualifier("smsNotificationSendStrategy") INotificationSendStrategy notificationSendStrategy,
      UserService userService) {
    this.smsNotificationService = smsNotificationService;
    this.notificationSendStrategy = notificationSendStrategy;
    this.userService = userService;
  }

  @Scheduled(fixedRate = 60000)
  private void scheduleNotificationSending() {
    List<SmsNotification> notifications = getNotifications();
    sendNotifications(notifications);
    updateNotifications(notifications);
  }

  private List<SmsNotification> getNotifications() {
    return smsNotificationService.getAllPendingNotificationsByLimit(PROVIDER_LIMIT_PER_MINUTE);
  }

  private void sendNotifications(List<SmsNotification> notifications) {
    for (Notification notification : notifications) {
      User user = userService.findUserById(notification.getUserId());
      notificationSendStrategy.send(user.getNumber(), notification.getMessage());
      notification.setSent(true);
    }
  }

  private void updateNotifications(List<SmsNotification> notifications) {
    smsNotificationService.updateMultipleNotifications(notifications);
  }
}
