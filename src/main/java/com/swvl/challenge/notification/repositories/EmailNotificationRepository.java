package com.swvl.challenge.notification.repositories;

import com.swvl.challenge.notification.models.EmailNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
  List<EmailNotification> findAllBySentIsFalse(Pageable page);
}
