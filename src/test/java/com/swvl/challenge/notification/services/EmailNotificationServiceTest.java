package com.swvl.challenge.notification.services;

import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.EmailNotification;
import com.swvl.challenge.notification.repositories.EmailNotificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmailNotificationServiceTest {

  @Autowired private EmailNotificationService service;
  @MockBean private EmailNotificationRepository repository;

  @Test
  @DisplayName("Test saveNotification")
  public void testSaveNotification() {
    // arrange
    NotificationRequestBody body = new NotificationRequestBody(1L, "Test notification message");
    EmailNotification expected = new EmailNotification(body.getUserId(), body.getMessage(), false);
    doReturn(expected).when(repository).saveAndFlush(any());

    // action
    EmailNotification actual = service.saveNotification(body);

    // assert
    verify(repository, times(1)).saveAndFlush(any());
    Assertions.assertSame(expected, actual, "Email notifications are not the same");
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
    List<EmailNotification> expected =
        Arrays.asList(
            new EmailNotification(body.get(0).getUserId(), body.get(0).getMessage(), false),
            new EmailNotification(body.get(1).getUserId(), body.get(1).getMessage(), false),
            new EmailNotification(body.get(2).getUserId(), body.get(2).getMessage(), false));
    doReturn(expected).when(repository).saveAll(any());

    // action
    List<EmailNotification> actual = service.saveMultipleNotifications(body);

    // assert
    verify(repository, times(1)).saveAll(anyList());
    Assertions.assertSame(expected, actual, "Email notification list are not the same");
  }

  @Test
  @DisplayName("Test getAllPendingNotificationsByLimit")
  public void testGetAllPendingNotificationsByLimit() {
    // arrange
    final int LIMIT = 2;
    Pageable page = PageRequest.of(0, LIMIT, Sort.by(Sort.Direction.ASC, "id"));
    List<EmailNotification> notifications =
        Arrays.asList(
            new EmailNotification(1L, "Test notification message #1", false),
            new EmailNotification(2L, "Test notification message #2", false),
            new EmailNotification(3L, "Test notification message #3", false),
            new EmailNotification(4L, "Test notification message #4", true));
    List<EmailNotification> expected =
        Arrays.asList(
            new EmailNotification(1L, "Test notification message #1", false),
            new EmailNotification(2L, "Test notification message #2", false));
    doReturn(expected).when(repository).findAllBySentIsFalse(any());

    // action
    List<EmailNotification> actual = service.getAllPendingNotificationsByLimit(LIMIT);

    // assert
    verify(repository, times(1)).findAllBySentIsFalse(page);
    Assertions.assertSame(expected, actual, "Email notification list are not the same");
  }

  @Test
  @DisplayName("Test updateMultipleNotifications")
  public void testUpdateMultipleNotifications() {
    // arrange
    List<EmailNotification> notifications =
        Arrays.asList(
            new EmailNotification(1L, "Test notification message #1", false),
            new EmailNotification(2L, "Test notification message #2", false),
            new EmailNotification(3L, "Test notification message #3", false),
            new EmailNotification(4L, "Test notification message #4", true));

    // action
    service.updateMultipleNotifications(notifications);

    // assert
    verify(repository, times(1)).saveAll(notifications);
  }
}
