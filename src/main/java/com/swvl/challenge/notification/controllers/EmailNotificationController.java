package com.swvl.challenge.notification.controllers;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.EmailNotification;
import com.swvl.challenge.notification.services.EmailNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/email")
public class EmailNotificationController {

  private final EmailNotificationService emailNotificationService;

  public EmailNotificationController(EmailNotificationService emailNotificationService) {
    this.emailNotificationService = emailNotificationService;
  }

  @PostMapping(value = "/")
  public ResponseEntity<EmailNotification> addNotification(
      @RequestBody NotificationRequestBody body) {
    EmailNotification notification = emailNotificationService.saveNotification(body);
    return ResponseEntity.status(HttpStatus.CREATED).body(notification);
  }

  @PostMapping(value = "/group")
  public ResponseEntity<List<EmailNotification>> addNotificationList(
      @RequestBody List<NotificationRequestBody> bodyList) {
    List<EmailNotification> notificationList =
        emailNotificationService.saveMultipleNotifications(bodyList);
    return ResponseEntity.status(HttpStatus.CREATED).body(notificationList);
  }
}
