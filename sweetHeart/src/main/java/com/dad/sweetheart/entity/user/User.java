package com.dad.sweetheart.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
  @Id
  private Long userID;
  private String userAccount;
  private String userPassword;
  private String createAt;
  private String updateAt;
  private Long updateBy;
  private Boolean isAlive;
  private String city;
  private String income;
  private String introduce;
}
