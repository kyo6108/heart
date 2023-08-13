package com.dad.sweetheart.exception;

import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Data
@Slf4j
public class SweetException extends Exception {
  private String errMsg;
  private List<String> errMsgList;

  /**
   * 塞錯誤資訊
   *
   * @param errMsg
   * @return
   */
  public SweetException addErrMsg(String errMsg) {
    this.setErrMsg(errMsg);
    log.warn("this : {}", this);
    return this;
  }

  /**
   * 塞錯誤資訊
   *
   * @param errMsgList
   * @return
   */
  public SweetException addErrMsgList(List<String> errMsgList) {
    this.setErrMsgList(errMsgList);
    log.warn("thisList : {}", this);
    return this;
  }

  /**
   * 丟出例外
   *
   * @throws Exception
   */
  public void excute() throws Exception {
    if (StringUtils.isNotEmpty(this.getErrMsg())
        || CollectionUtils.isNotEmpty(this.getErrMsgList())) {
      throw this;
    }
  }
}
