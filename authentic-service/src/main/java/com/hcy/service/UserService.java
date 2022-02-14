package com.hcy.service;

import com.hcy.mapper.CredentialsMapper;
import com.hcy.pojo.Credentials;
import com.hcy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private CredentialsMapper credentialsMapper;
	// 自定义用户，用户名随便，密码指定为123
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsMapper.findCredentialsByName(username);
        if (credentials == null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        credentials.getAuthorities().forEach(s -> {
            authorities.add(new SimpleGrantedAuthority(s));
        });
        return new User(credentials.getName(), credentials.getPassword(), credentials.getEnabled(),authorities);
    }
}