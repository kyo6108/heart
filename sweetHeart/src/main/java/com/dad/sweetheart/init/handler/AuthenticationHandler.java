package com.dad.sweetheart.init.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.dad.sweetheart.beanVo.TokenVo;
import com.dad.sweetheart.init.token.TokenUtil;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authentication)
      throws IOException, ServletException {
    String token = StringUtils.EMPTY;
    try {
      // 身份主體
      Object principal = authentication.getPrincipal();
      if (principal instanceof UserDetails) {
        UserDetails user = (UserDetails) principal;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authoritiesList = new ArrayList<>(authorities.size());
        authorities.forEach(a -> authoritiesList.add(a.getAuthority()));
        Date now = new Date();
        Date endTime = DateUtil.offsetSecond(now, 60 * 60);
        TokenVo tokenVo =
            TokenVo.builder()
                .sub(user.getUsername())
                .start(now.getTime())
                .end(endTime.getTime())
                .jwtIdKey(UUID.randomUUID().toString())
                .userName(user.getUsername())
                .authorities(authoritiesList)
                .build();
        token =
            TokenUtil.generateToken(
                JSONUtil.toJsonStr(tokenVo), SecureUtil.md5(TokenUtil.HEADER_STRING));
      }
      response.setHeader(TokenUtil.HEADER_STRING, token);
      response.setContentType("application/json; charset=UTF-8");
    } catch (JOSEException e) {
      log.error("JOSEException error message :{}", e.getMessage());
      throw new IOException(e.getMessage());
    } catch (Exception e) {
      log.error("error ! message :{}", e.getMessage(), e);
      throw new ServletException(e.getMessage());
    }
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {}
}
