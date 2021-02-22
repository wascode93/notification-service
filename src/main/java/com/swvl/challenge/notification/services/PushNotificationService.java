package com.swvl.challenge.notification.services;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.PushNotification;
import com.swvl.challenge.notification.models.SmsNotification;
import com.swvl.challenge.notification.repositories.PushNotificationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PushNotificationService {

  private final PushNotificationRepository pushNotificationRepository;

  public PushNotificationService(PushNotificationRepository pushNotificationRepository) {
    this.pushNotificationRepository = pushNotificationRepository;
  }

  public PushNotification saveNotification(NotificationRequestBody body) {
    PushNotification notification = new PushNotification();

    notification.setMessage(body.getMessage());
    notification.setUserId(body.getUserId());

    return pushNotificationRepository.saveAndFlush(notification);
  }

  public List<PushNotification> saveMultipleNotifications(List<NotificationRequestBody> bodyList) {
    List<PushNotification> notificationList = new ArrayList<>();
    for (NotificationRequestBody body : bodyList) {
      PushNotification notification = new PushNotification();

      notification.setMessage(body.getMessage());
      notification.setUserId(body.getUserId());

      notificationList.add(notification);
    }
    return pushNotificationRepository.saveAll(notificationList);
  }

  public List<PushNotification> getAllPendingNotificationsByLimit(int limit) {
    return pushNotificationRepository.findAllBySentIsFalse(
        PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id")));
  }

  public void updateMultipleNotifications(List<PushNotification> notifications) {
    this.pushNotificationRepository.saveAll(notifications);
  }

  public List<PushNotification> getUserSentNotifications(Long userId) {
    int pageLimit = 30;
    return pushNotificationRepository.findAllByUserIdAndSentIsTrue(
        userId, PageRequest.of(0, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
  }
}
