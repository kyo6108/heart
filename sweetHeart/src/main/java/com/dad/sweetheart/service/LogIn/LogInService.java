package com.dad.sweetheart.service.LogIn;

import com.dad.sweetheart.check.user.UserCheck;
import com.dad.sweetheart.entity.user.User;
import com.dad.sweetheart.repostiry.user.UserRepostiry;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登入流程實作Service
 */
@Slf4j
@Service
public class LogInService {

  @Autowired private UserCheck userCheck;
  @Autowired private UserRepostiry userRepostiry;

  /**
   * 使用者登入，檢核是否為有效使用者
   *
   * @param user 前端傳遞之使用者資訊物件
   * @return 使用者物件
   * @throws Exception
   */
  public User userLogin(User user) throws Exception {
    // check參數是否正確，有錯誤即拋出例外
    userCheck.checkLogInParameter(user).excute();
    // 獲取 db user的資訊
    return userRepostiry.findByUserId(user.getUserID());
  }

  /**
   * 註解示範
   * @param map 前端json request
   * @param webList 前端 checkBox裡的id資訊
   * @throws Exception
   */
  public void getInfoFromWeb(Map<String,Object> map , List<Long> webList) throws Exception{
    // user 帳號資訊
    String user = MapUtils.getString(map,"user", StringUtils.EMPTY);
    // user 密碼資訊
    String password = MapUtils.getString(map,"password", StringUtils.EMPTY);
    // 帳號是否驗證通過
    Boolean isActive = MapUtils.getBoolean(map,"isActive", false);

    // checkBox的預設資料
    int defaultValue = 0;
    // checkBox裡的Id依業務需求進行加總
    if (CollectionUtils.isNotEmpty(webList)){
      for (Long val :webList ) {
          defaultValue+=val;
      }
    }
  }
}
