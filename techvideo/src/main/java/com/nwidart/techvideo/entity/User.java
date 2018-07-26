package com.nwidart.techvideo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;

  public User() {
  }
}
