package com.swvl.challenge.notification.repositories;

import com.swvl.challenge.notification.models.SmsNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsNotificationRepository extends JpaRepository<SmsNotification, Long> {
  List<SmsNotification> findAllBySentIsFalse(Pageable page);
}
