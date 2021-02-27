package com.swvl.challenge.notification.repositories;

import com.swvl.challenge.notification.models.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository<T extends Notification> extends JpaRepository<T, Long> {
  List<T> findAllBySentIsFalse(Pageable page);
}
