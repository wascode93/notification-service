package com.swvl.challenge.notification.services;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.SmsNotification;
import com.swvl.challenge.notification.repositories.SmsNotificationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsNotificationService {

  private final SmsNotificationRepository smsNotificationRepository;

  public SmsNotificationService(SmsNotificationRepository smsNotificationRepository) {
    this.smsNotificationRepository = smsNotificationRepository;
  }

  public SmsNotification saveNotification(NotificationRequestBody body) {
    SmsNotification notification = new SmsNotification();

    notification.setMessage(body.getMessage());
    notification.setUserId(body.getUserId());

    return smsNotificationRepository.saveAndFlush(notification);
  }

  public List<SmsNotification> saveMultipleNotifications(List<NotificationRequestBody> bodyList) {
    List<SmsNotification> notificationList = new ArrayList<>();
    for (NotificationRequestBody body : bodyList) {
      SmsNotification notification = new SmsNotification();

      notification.setMessage(body.getMessage());
      notification.setUserId(body.getUserId());

      notificationList.add(notification);
    }
    return smsNotificationRepository.saveAll(notificationList);
  }

  public List<SmsNotification> getAllPendingNotificationsByLimit(int limit) {
    return smsNotificationRepository.findAllBySentIsFalse(
        PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id")));
  }

  public void updateMultipleNotifications(List<SmsNotification> notifications){
    this.smsNotificationRepository.saveAll(notifications);
  }
}
