package com.dad.sweetheart.service.LogIn;

import com.dad.sweetheart.check.user.UserCheck;
import com.dad.sweetheart.entity.user.User;
import com.dad.sweetheart.repostiry.user.UserRepostiry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogInService {

  @Autowired private UserCheck userCheck;
  @Autowired private UserRepostiry userRepostiry;

  public void userLogin(User user) throws Exception {
    // check參數是否正確，有錯誤即拋出例外
    userCheck.checkLogInParameter(user).excute();
    // 獲取 db user的資訊
    User queryUser = userRepostiry.findByUserId(user.getUserID());
  }
}
