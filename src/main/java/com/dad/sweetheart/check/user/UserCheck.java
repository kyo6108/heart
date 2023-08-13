package com.dad.sweetheart.check.user;

import com.dad.sweetheart.entity.user.User;
import com.dad.sweetheart.exception.SweetException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCheck {
  private static final String ACCOUNT_ERROR_MSG = "帳號不可為空 !";
  private static final String PASSWORD_ERROR_MSG = "密碼不可為空 !";

  private static final String SYSTEM_ERROR = "系統錯誤，請聯繫管理員 !";

  private static final int BASE_COUNT = 0;

  /**
   * 確認使用者輸入的參數
   *
   * @param user
   * @throws Exception
   */
  public SweetException checkLogInParameter(User user) throws Exception {
    SweetException sweetException = new SweetException();
    Long id = user.getUserID();
    if (id == null || id == BASE_COUNT || id < BASE_COUNT) {
      sweetException.setErrMsg(SYSTEM_ERROR);
      return sweetException;
    }

    if (StringUtils.isBlank(user.getUserAccount())) {
      sweetException.setErrMsg(ACCOUNT_ERROR_MSG);
    }
    if (StringUtils.isBlank(user.getUserPassword())) {
      sweetException.setErrMsg(PASSWORD_ERROR_MSG);
    }
    return sweetException;
  }
}
