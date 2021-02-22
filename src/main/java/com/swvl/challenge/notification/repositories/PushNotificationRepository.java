package com.swvl.challenge.notification.repositories;

import com.swvl.challenge.notification.models.PushNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {
  List<PushNotification> findAllBySentIsFalse(Pageable page);
  List<PushNotification> findAllByUserIdAndSentIsTrue(Long userId, Pageable page);
}
