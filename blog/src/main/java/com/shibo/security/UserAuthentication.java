package com.shibo.security;


import com.shibo.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author shibo
 */
public class UserAuthentication implements Authentication {
    private final User user;
    private boolean authenticated = true;
    private List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

    public UserAuthentication(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public Object getCredentials() {
        return user.getToken();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    /**
     * 使得标注 @AuthenticationPrincipal (@CurrentUser) 可以使用
     */
    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
