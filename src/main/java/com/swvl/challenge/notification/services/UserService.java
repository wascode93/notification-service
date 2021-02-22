package com.swvl.challenge.notification.services;

import com.swvl.challenge.notification.models.User;
import com.swvl.challenge.notification.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findUserById(Long id) {
    return userRepository.findById(id).get();
  }
}
