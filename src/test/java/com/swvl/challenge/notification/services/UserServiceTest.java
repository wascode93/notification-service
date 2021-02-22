package com.swvl.challenge.notification.services;

import com.swvl.challenge.notification.models.User;
import com.swvl.challenge.notification.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
  @Autowired private UserService userService;
  @MockBean private UserRepository userRepository;

  @Test
  @DisplayName("Test findUserById")
  public void testFindUserById() {
    // arrange
    User expectedUser = new User(1L, "test@example.com", "+201235488976", "DEVICE_TOKEN");
    doReturn(Optional.of(expectedUser)).when(userRepository).findById(1L);

    // action
    User actualUser = userService.findUserById(1L);

    // assert
    Assertions.assertSame(expectedUser, actualUser, "Users are not the same");
  }
}
