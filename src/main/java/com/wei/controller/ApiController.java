package com.wei.controller;

import com.wei.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ApiController {

    /**
     * url /user/info 获取用户信息 具有用户密码，生产环境需要关闭
     * @return
     */
    @GetMapping("/info")
    public static Object getLoginUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //
            if (principal instanceof String){
                return new User();
            }
            else if (principal != null) {
                User user =(User)principal;
                if (user != null) {
                    return user;
                }
                return new User();
            }
        } catch (Exception ex) {
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }
}
