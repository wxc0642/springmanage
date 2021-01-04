package com.wei.service;

import com.wei.dao.UserDao;
import com.wei.pojo.CustomUser;
import com.wei.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class SignUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    CustomUser customUser;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser customUser=userDao.searchUser(username);
        if (customUser==null){
            return null;
        }else {
            Collection<GrantedAuthority> authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userDao.searchLimit(username).getRole()));
            String password=customUser.getPassword();
            User user=new User(customUser.getUsername(),passwordEncoder.encode(password),authorities);
            return user;
        }
    }
}
