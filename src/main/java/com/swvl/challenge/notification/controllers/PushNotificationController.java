package com.swvl.challenge.notification.controllers;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.PushNotification;
import com.swvl.challenge.notification.services.PushNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/push")
public class PushNotificationController {

  private final PushNotificationService pushNotificationService;

  public PushNotificationController(PushNotificationService pushNotificationService) {
    this.pushNotificationService = pushNotificationService;
  }

  @GetMapping(value = "/{userId}")
  public ResponseEntity<List<PushNotification>> readUserNotifications(@PathVariable Long userId) {
    List<PushNotification> notificationList =
        pushNotificationService.getUserSentNotifications(userId);
    return ResponseEntity.status(HttpStatus.OK).body(notificationList);
  }

  @PostMapping(value = "/")
  public ResponseEntity<PushNotification> addNotification(
      @RequestBody NotificationRequestBody body) {
    PushNotification notification = pushNotificationService.saveNotification(body);
    return ResponseEntity.status(HttpStatus.CREATED).body(notification);
  }

  @PostMapping(value = "/group")
  public ResponseEntity<List<PushNotification>> addNotificationList(
      @RequestBody List<NotificationRequestBody> bodyList) {
    List<PushNotification> notificationList =
        pushNotificationService.saveMultipleNotifications(bodyList);
    return ResponseEntity.status(HttpStatus.CREATED).body(notificationList);
  }
}
