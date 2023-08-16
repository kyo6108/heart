package com.dad.sweetheart.init.token;

import cn.hutool.json.JSONUtil;
import com.dad.sweetheart.beanVo.TokenVo;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtil {
  // token前墜
  private static final String TOKEN_PREFIX = "Bearer";
  // request header token key
  public static final String HEADER_STRING = "Authorization";
  // private key，解密/加密使用
  private static final Key KEY = MacProvider.generateKey();

  private static final String TOKEN_ERROR = "toke簽名不合法!";
  private static final String TOKEN_EXPIRED = "toke過期";


  /**
   * 建立Token
   *
   * @param payloadStr 有效酬載
   * @param secret 金鑰
   * @return JWS的字串
   * @throws Exception
   */
  public static String generateToken(String payloadStr, String secret) throws Exception {
    // 設立標頭，並且設定簽名演算法跟類型
    JWSHeader jwsHeader =
        new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
    // 封裝資訊(酬載)
    Payload payload = new Payload(payloadStr);
    // 物件建立
    JWSObject jwsObject = new JWSObject(jwsHeader, payload);
    // 簽名器初始化，塞入鑰匙
    JWSSigner jwsSigner = new MACSigner(secret);
    // 簽名
    jwsObject.sign(jwsSigner);
    // 序列化回傳
    return jwsObject.serialize();
  }

  /**
   * toke驗證，回傳自定義好的Token物件
   *
   * @param token
   * @param secret
   * @return
   * @throws Exception
   */
  public static TokenVo verifyToken(String token, String secret) throws Exception {
    // 解析
    JWSObject jwsObject = JWSObject.parse(token);
    // 驗證器設定
    JWSVerifier jwsVerifier = new MACVerifier(secret);

    if (!jwsObject.verify(jwsVerifier)) {
      throw new JOSEException(TOKEN_ERROR);
    }
    String payload = String.valueOf(jwsObject.getPayload());
    // 物件轉換
    TokenVo tokenVo = JSONUtil.toBean(payload, TokenVo.class);
    if (tokenVo.getEnd() < new Date().getTime()) {
      throw new JOSEException(TOKEN_EXPIRED);
    }
    return tokenVo;
  }
}
