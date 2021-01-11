package com.wei.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
//根据SpringSecurity提供的users.ddl创建实体类User
public class User implements UserDetails {

    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;
    private final static boolean accountNonExpired=true;
    private final static boolean accountNonLocked=true;
    private final static boolean credentialsNonExpired=true;
    private final static boolean enabled=true;


    public User() {
    }

    public User(String username, String password,Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
