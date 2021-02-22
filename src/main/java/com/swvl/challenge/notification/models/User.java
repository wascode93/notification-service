package com.swvl.challenge.notification.models;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  @Column(name = "mobile_number")
  private String number;

  private String token;

  public User() {}

  public User(Long id, String email, String number, String token) {
    this.id = id;
    this.email = email;
    this.number = number;
    this.token = token;
  }

  public String getEmail() {
    return email;
  }

  public String getNumber() {
    return number;
  }

  public String getToken() {
    return token;
  }
}
