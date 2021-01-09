package com.wei.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 获取当前用户principal
 */
@Service
public class GetUserPrincipal {
    public String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

             String username = ((UserDetails)principal).getUsername();
             return username;

        } else {
            String username = principal.toString();
            return username;
        }
    }

}
