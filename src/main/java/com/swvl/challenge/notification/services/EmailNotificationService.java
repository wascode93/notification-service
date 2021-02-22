package com.swvl.challenge.notification.services;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.EmailNotification;
import com.swvl.challenge.notification.models.Notification;
import com.swvl.challenge.notification.repositories.EmailNotificationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailNotificationService {

  private final EmailNotificationRepository emailNotificationRepository;

  public EmailNotificationService(EmailNotificationRepository emailNotificationRepository) {
    this.emailNotificationRepository = emailNotificationRepository;
  }

  public EmailNotification saveNotification(NotificationRequestBody body) {
    EmailNotification notification = new EmailNotification();

    notification.setMessage(body.getMessage());
    notification.setUserId(body.getUserId());

    return emailNotificationRepository.saveAndFlush(notification);
  }

  public List<EmailNotification> saveMultipleNotifications(List<NotificationRequestBody> bodyList) {
    List<EmailNotification> notificationList = new ArrayList<>();
    for (NotificationRequestBody body : bodyList) {
      EmailNotification notification = new EmailNotification();

      notification.setMessage(body.getMessage());
      notification.setUserId(body.getUserId());

      notificationList.add(notification);
    }
    return emailNotificationRepository.saveAll(notificationList);
  }

  public List<EmailNotification> getAllPendingNotificationsByLimit(int limit) {
    return emailNotificationRepository.findAllBySentIsFalse(
        PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id")));
  }

  public void updateMultipleNotifications(List<EmailNotification> notifications) {
    this.emailNotificationRepository.saveAll(notifications);
  }
}
