package com.shibo.conf;

import com.shibo.security.AuthenticationFilter;
import com.shibo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    // configure how requests are secured by interceptors.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(SIKE_REQUEST_MATCHER).authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
                .and()
                .httpBasic()
                .and()
                //.csrf().ignoringAntMatchers("/druid/**")// /druid/basic.json 请求不做 CSRF 控制
                //.and()
                .headers().contentTypeOptions().disable()// 关闭 X-Content-Type-Options:nosniff ，使 Druid 页面可以正常显示
                .and()
                .headers().frameOptions().disable()
        ;
        http.addFilterBefore(new AuthenticationFilter(SIKE_REQUEST_MATCHER).setUserService(userService), BasicAuthenticationFilter.class);
    }


    public static String[] protectedGetPaths() {
        return new String[]{
                "/user/login"
        };
    }

    public static String[] protectedPostPaths() {
        return new String[]{
        };
    }

    private static String[] PROTECTED_DELETE_PATHS = {"/sns/comments/{commentId}",
    "/user_admin/delete"};

    public static String[] protectedDeletePaths() {
        return PROTECTED_DELETE_PATHS;
    }

    public static final Map<HttpMethod, String[]> AUTH_MATCHER_MAP = new TreeMap<HttpMethod, String[]>(){{
        put(HttpMethod.GET, protectedGetPaths());
        put(HttpMethod.POST, protectedPostPaths());
        put(HttpMethod.DELETE, protectedDeletePaths());
    }};

    public static final RequestMatcher SIKE_REQUEST_MATCHER = new OrRequestMatcher(AUTH_MATCHER_MAP.entrySet()
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

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }


}
