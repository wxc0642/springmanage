package com.wei.config;


import com.wei.service.SignUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    //放开资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/images/**");
    }

    //授权
    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        //对应权限的人能够访问
       //请求授权规则
        http.authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/student/**").hasAuthority("student")
                .antMatchers("/teacher/**").hasAuthority("teacher")
                .antMatchers("/administrator/**").hasAuthority("administrator")
                .antMatchers("/shared/info").hasAnyAuthority("student","teacher","administrator");

        //解决网页嵌套问题
        http.headers().frameOptions().disable();
        //没有权限，跳到默认页面（是否有必要？有，防止从url直接登录），开启登录页面[loginPage()返回到自己写的页面]
        http.formLogin().loginPage("/toLogin");


        //关闭crsf
        http.csrf().disable();
        //注销
        http.logout().logoutSuccessUrl("/toLogin");

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
    }

    @Autowired
    SignUserDetailsService signUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //认证
    //密码编码
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(signUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }





}
