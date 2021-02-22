package com.swvl.challenge.notification.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.PushNotification;
import com.swvl.challenge.notification.services.PushNotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PushNotificationControllerTest {
  @MockBean private PushNotificationService service;
  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("POST /api/v1/push/ Add SmsNotification")
  public void testAddPushNotification() throws Exception {
    // arrange
    NotificationRequestBody body = new NotificationRequestBody(1L, "Test notification");
    PushNotification expected = new PushNotification(body.getUserId(), body.getMessage(), false);
    doReturn(expected).when(service).saveNotification(body);

    // action
    mockMvc
        .perform(
            post("/api/v1/push/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body)))
        .andExpect(status().isCreated());

    // assert
    verify(service, times(1)).saveNotification(any());
  }

  @Test
  @DisplayName("POST /api/v1/push/group Add SmsNotification Group")
  public void testAddPushNotificationGroup() throws Exception {
    // arrange
    List<NotificationRequestBody> bodyList =
        Arrays.asList(
            new NotificationRequestBody(1L, "Test notification #1"),
            new NotificationRequestBody(2L, "Test notification #2"));
    List<PushNotification> expected =
        Arrays.asList(
            new PushNotification(bodyList.get(0).getUserId(), bodyList.get(0).getMessage(), false),
            new PushNotification(bodyList.get(1).getUserId(), bodyList.get(1).getMessage(), false));
    doReturn(expected).when(service).saveMultipleNotifications(bodyList);

    // action
    mockMvc
        .perform(
            post("/api/v1/push/group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bodyList)))
        .andExpect(status().isCreated());

    // assert
    verify(service, times(1)).saveMultipleNotifications(anyList());
  }

  @Test
  @DisplayName("GET /api/v1/push/{userId} Read user's push notifications")
  public void testReadUserNotifications() throws Exception {
    // arrange
    Long userId = 2L;
    List<PushNotification> expected =
        Arrays.asList(
            new PushNotification(2L, "Test notification #1", true),
            new PushNotification(2L, "Test notification #2", true),
            new PushNotification(2L, "Test notification #3", true),
            new PushNotification(2L, "Test notification #4", false));
    doReturn(expected).when(service).getUserSentNotifications(userId);

    // action
    mockMvc.perform(get("/api/v1/push/{userId}", userId)).andExpect(status().isOk());

    // assert
    verify(service, times(1)).getUserSentNotifications(userId);
  }
}
