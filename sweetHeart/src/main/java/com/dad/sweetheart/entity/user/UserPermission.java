package com.dad.sweetheart.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission {
  private Long permissionsId;
  private Long userId;
  private Boolean isvip;
  private String vipCreateAt;
  private String vipUpdateAt;
  private String vipEndedAt;
  private Boolean sex;
  private String height;
  private String weight;
  private Integer age;
  private String couple;
}
