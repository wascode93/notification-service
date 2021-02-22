package com.swvl.challenge.notification.controllers;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.Notification;
import com.swvl.challenge.notification.models.SmsNotification;
import com.swvl.challenge.notification.services.SmsNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sms")
public class SmsNotificationController {

  private final SmsNotificationService smsNotificationService;

  public SmsNotificationController(SmsNotificationService smsNotificationService) {
    this.smsNotificationService = smsNotificationService;
  }

  @PostMapping(value = "/")
  public ResponseEntity<SmsNotification> addNotification(
      @RequestBody NotificationRequestBody body) {
    SmsNotification notification = smsNotificationService.saveNotification(body);
    return ResponseEntity.status(HttpStatus.CREATED).body(notification);
  }

  @PostMapping(value = "/group")
  public ResponseEntity<List<SmsNotification>> addNotificationList(
      @RequestBody List<NotificationRequestBody> bodyList) {
    List<SmsNotification> notificationList =
        smsNotificationService.saveMultipleNotifications(bodyList);
    return ResponseEntity.status(HttpStatus.CREATED).body(notificationList);
  }
}
