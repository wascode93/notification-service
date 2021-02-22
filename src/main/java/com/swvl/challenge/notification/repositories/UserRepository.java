package com.swvl.challenge.notification.repositories;

import com.swvl.challenge.notification.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
