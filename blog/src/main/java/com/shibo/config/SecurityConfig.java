package com.shibo.config;

import com.google.common.collect.ImmutableMap;
import com.shibo.security.AuthenticationFilter;
import com.shibo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;
import java.util.Map;

/**
 * @author shibo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(REQUEST_MATCHER).authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
        http.addFilterBefore(new AuthenticationFilter(REQUEST_MATCHER).setUserService(userService), BasicAuthenticationFilter.class);
    }

    private static String[] PROTECTED_GET_PATHS = {"/user/info"};

    private static String[] PROTECTED_POST_PATHS = {""};

    private static String[] PROTECTED_DELETE_PATHS = {""};


    /*public static final Map<HttpMethod, String[]> AUTH_MATCHER_MAP = ImmutableMap.of(
            HttpMethod.GET, PROTECTED_GET_PATHS,
            HttpMethod.POST, PROTECTED_POST_PATHS,
            HttpMethod.DELETE, PROTECTED_DELETE_PATHS
    );*/
    public static final Map<HttpMethod, String[]> AUTH_MATCHER_MAP = ImmutableMap.of(
            HttpMethod.GET, PROTECTED_GET_PATHS
    );


    public static final RequestMatcher REQUEST_MATCHER = new OrRequestMatcher(AUTH_MATCHER_MAP.entrySet()
            .stream()
            .map(entry -> mapToRequestMatcherArray(entry.getKey(), entry.getValue()))
            .flatMap(Arrays::stream)
            .toArray(RequestMatcher[]::new)
    );

    private static RequestMatcher[] mapToRequestMatcherArray(final HttpMethod httpMethod, String[] urlPatterns) {
        return Arrays.stream(urlPatterns)
                .map(urlPattern -> new AntPathRequestMatcher(urlPattern, httpMethod.name()))
                .toArray(RequestMatcher[]::new);
    }
}