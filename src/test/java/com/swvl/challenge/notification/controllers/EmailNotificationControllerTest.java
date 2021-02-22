package com.swvl.challenge.notification.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swvl.challenge.notification.dto.NotificationRequestBody;
import com.swvl.challenge.notification.models.EmailNotification;
import com.swvl.challenge.notification.models.SmsNotification;
import com.swvl.challenge.notification.services.EmailNotificationService;
import com.swvl.challenge.notification.services.SmsNotificationService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmailNotificationControllerTest {
  @MockBean private EmailNotificationService service;
  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("POST /api/v1/email/ Add SmsNotification")
  public void testAddEmailNotification() throws Exception {
    // arrange
    NotificationRequestBody body = new NotificationRequestBody(1L, "Test notification");
    EmailNotification expected = new EmailNotification(body.getUserId(), body.getMessage(), false);
    doReturn(expected).when(service).saveNotification(body);

    // action
    mockMvc
        .perform(
            post("/api/v1/email/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body)))
        .andExpect(status().isCreated());

    // assert
    verify(service, times(1)).saveNotification(any());
  }

  @Test
  @DisplayName("POST /api/v1/email/group Add SmsNotification Group")
  public void testAddEmailNotificationGroup() throws Exception {
    // arrange
    List<NotificationRequestBody> bodyList =
        Arrays.asList(
            new NotificationRequestBody(1L, "Test notification #1"),
            new NotificationRequestBody(2L, "Test notification #2"));
    List<EmailNotification> expected =
        Arrays.asList(
            new EmailNotification(bodyList.get(0).getUserId(), bodyList.get(0).getMessage(), false),
            new EmailNotification(
                bodyList.get(1).getUserId(), bodyList.get(1).getMessage(), false));
    doReturn(expected).when(service).saveMultipleNotifications(bodyList);

    // action
    mockMvc
        .perform(
            post("/api/v1/email/group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bodyList)))
        .andExpect(status().isCreated());

    // assert
    verify(service, times(1)).saveMultipleNotifications(anyList());
  }
}
