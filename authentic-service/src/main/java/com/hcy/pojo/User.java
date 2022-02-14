package com.hcy.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/13 16:32
 */
public class User implements UserDetails {
    private String username;
    private String password;
    private Integer isEnabled;
    private List<GrantedAuthority> authoritiesList;

    public User(String username, String password,Integer isEnabled, List<GrantedAuthority> authoritiesList) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.authoritiesList = authoritiesList;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<GrantedAuthority> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritiesList;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authoritiesList=" + authoritiesList +
                '}';
    }
}
