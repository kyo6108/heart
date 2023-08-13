package com.dad.sweetheart.init.aop;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AopResult {
  // http return狀態碼
  private HttpStatusCode httpStatusCode;
  // 額外自定義的errCode
  private String statusCode;
  // 錯誤訊息，有例外時使用
  private String errMsg;
  // 取前端溝通用的json Map
  private Map<String, Object> rtnMap;
  // list版的錯誤訊息
  private List<String> errMsgList;
  // 主要for 查詢用的結果
  private List<?> resultList;


  /**
   * 確認token的驗證狀態
   */
  public void doCheckAuth(){

  }
}
