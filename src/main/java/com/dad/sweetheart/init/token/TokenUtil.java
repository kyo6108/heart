package com.dad.sweetheart.init.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.List;
import javax.security.sasl.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@Slf4j
public class TokenUtil {
  // token前墜
  private static final String TOKEN_PREFIX = "Bearer";
  // request header token key
  private static final String HEADER_STRING = "Authorization";
  // private key，解密/加密使用
  private static final Key KEY = MacProvider.generateKey();

  /**
   * 拿到token
   *
   * @param authentication
   * @return
   */
  public static String getToken(Authentication authentication) {
    try {
      return Jwts.builder()
          .setSubject(authentication.getName())
          .setExpiration(new Date(System.currentTimeMillis()))
          .signWith(SignatureAlgorithm.HS256, KEY)
          .compact();
    } catch (Exception e) {
      log.error("create jwt Token error ! message :{}", e.getMessage(), e);
      return StringUtils.EMPTY;
    }
  }

  public static Authentication authenticationToken(HttpServletRequest request)
      throws AuthenticationException, JwtException {
    // 先拿到token
    String token = request.getHeader(HEADER_STRING);
    if (StringUtils.isNotEmpty(token)) {
      // 非空則開始解析驗證

      try {
        // 解析token且去掉前贅字
        Claims claims =
            Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, StringUtils.EMPTY))
                .getBody();
        // 拿request的資訊
        String user = claims.getSubject();
        List<GrantedAuthority> authorityList =
            AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorize"));

        return user != null
            ? new UsernamePasswordAuthenticationToken(user, null, authorityList)
            : null;

      } catch (Exception e) {
    log.error("authenticationToken error ! message :{}" ,e.getMessage(),e);
    throw e;
      }
    }else{
      throw new AuthenticationException("token is empty ! HttpServletRequest  :" + request);
    }
  }
}
