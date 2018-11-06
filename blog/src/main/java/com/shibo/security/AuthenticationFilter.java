package com.shibo.security;

import com.alibaba.fastjson.JSON;
import com.shibo.common.Constants;
import com.shibo.common.JsonResult;
import com.shibo.common.ResultCode;
import com.shibo.entity.User;
import com.shibo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shibo
 */
@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userService;

    public AuthenticationFilter setUserService(UserService userService){
        this.userService = userService;
        return this;
    }

    public AuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(Constants.HTTP_HEADER_X_AUTH_TOKEN);

        if (StringUtils.isEmpty(token)) {
            throw new BadCredentialsException("Invalid token");
        }
        // 识别用户身份
        User user = userService.findByToken(token);
        // 用户不存在
        if (user == null) {
            throw new BadCredentialsException("User not exist for the token");
        }

        return new UserAuthentication(user);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString());
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
        }

        String apiResponseStr = JSON.toJSONString(new JsonResult(ResultCode.ERROR, "authentication error"));
        if (failed instanceof BadCredentialsException) {
            log.warn("{}: {}", apiResponseStr, failed.toString());
        } else {
            log.error(apiResponseStr, failed);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(apiResponseStr);
    }

}
