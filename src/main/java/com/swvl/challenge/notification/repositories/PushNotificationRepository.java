package com.swvl.challenge.notification.repositories;

import com.swvl.challenge.notification.models.PushNotification;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PushNotificationRepository extends NotificationRepository<PushNotification> {
  List<PushNotification> findAllByUserIdAndSentIsTrue(Long userId, Pageable page);
}
