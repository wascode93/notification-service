package com.swvl.challenge.notification.services;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.SmsNotification;
import com.swvl.challenge.notification.repositories.SmsNotificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SmsNotificationServiceTest {

  @Autowired private SmsNotificationService service;
  @MockBean private SmsNotificationRepository repository;

  @Test
  @DisplayName("Test saveNotification")
  public void testSaveNotification() {
    // arrange
    NotificationRequestBody body = new NotificationRequestBody(1L, "Test notification message");
    SmsNotification expected = new SmsNotification(body.getUserId(), body.getMessage(), false);
    doReturn(expected).when(repository).saveAndFlush(any());

    // action
    SmsNotification actual = service.saveNotification(body);

    // assert
    verify(repository, times(1)).saveAndFlush(any());
    Assertions.assertSame(expected, actual, "Sms notifications are not the same");
  }

  @Test
  @DisplayName("Test saveMultipleNotifications")
  public void testSaveMultipleNotifications() {
    // arrange
    List<NotificationRequestBody> body =
        Arrays.asList(
            new NotificationRequestBody(1L, "Test notification message #1"),
            new NotificationRequestBody(2L, "Test notification message #2"),
            new NotificationRequestBody(3L, "Test notification message #3"));
    List<SmsNotification> expected =
        Arrays.asList(
            new SmsNotification(body.get(0).getUserId(), body.get(0).getMessage(), false),
            new SmsNotification(body.get(1).getUserId(), body.get(1).getMessage(), false),
            new SmsNotification(body.get(2).getUserId(), body.get(2).getMessage(), false));
    doReturn(expected).when(repository).saveAll(any());

    // action
    List<SmsNotification> actual = service.saveMultipleNotifications(body);

    // assert
    verify(repository, times(1)).saveAll(anyList());
    Assertions.assertSame(expected, actual, "Sms notification list are not the same");
  }

  @Test
  @DisplayName("Test getAllPendingNotificationsByLimit")
  public void testGetAllPendingNotificationsByLimit() {
    // arrange
    final int LIMIT = 2;
    Pageable page = PageRequest.of(0, LIMIT, Sort.by(Sort.Direction.ASC, "id"));
    List<SmsNotification> notifications =
        Arrays.asList(
            new SmsNotification(1L, "Test notification message #1", false),
            new SmsNotification(2L, "Test notification message #2", false),
            new SmsNotification(3L, "Test notification message #3", false),
            new SmsNotification(4L, "Test notification message #4", true));
    List<SmsNotification> expected =
        Arrays.asList(
            new SmsNotification(1L, "Test notification message #1", false),
            new SmsNotification(2L, "Test notification message #2", false));
    doReturn(expected).when(repository).findAllBySentIsFalse(any());

    // action
    List<SmsNotification> actual = service.getAllPendingNotificationsByLimit(LIMIT);

    // assert
    verify(repository, times(1)).findAllBySentIsFalse(page);
    Assertions.assertSame(expected, actual, "Sms notification list are not the same");
  }

  @Test
  @DisplayName("Test updateMultipleNotifications")
  public void testUpdateMultipleNotifications() {
    // arrange
    List<SmsNotification> notifications =
        Arrays.asList(
            new SmsNotification(1L, "Test notification message #1", false),
            new SmsNotification(2L, "Test notification message #2", false),
            new SmsNotification(3L, "Test notification message #3", false),
            new SmsNotification(4L, "Test notification message #4", true));

    // action
    service.updateMultipleNotifications(notifications);

    // assert
    verify(repository, times(1)).saveAll(notifications);
  }
}
