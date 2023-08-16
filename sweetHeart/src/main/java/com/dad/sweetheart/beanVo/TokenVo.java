package com.dad.sweetheart.beanVo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class TokenVo {
    // token主題
    private String sub;
    // 簽發時間戳記
private Long start;
    // 過期時間
    private Long end;
    // jwtID
    private String jwtIdKey;
    // 使用者名稱
    private String userName;
    // 允許的許可權
    private List<String> authorities;
}
