package com.dad.sweetheart.controller.logIn;

import com.dad.sweetheart.entity.user.User;
import com.dad.sweetheart.init.aop.AopResult;
import com.dad.sweetheart.service.LogIn.LogInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sweetHeart")
public class LogInController {
  @Autowired private LogInService logInService;

  @GetMapping("/logIn")
  public void userLogin(@RequestBody User user, AopResult aopResult) {
    try {

    } catch (Exception e) {
      log.error("error ! messahe {}", e.getMessage(), e);
    }
  }
}
