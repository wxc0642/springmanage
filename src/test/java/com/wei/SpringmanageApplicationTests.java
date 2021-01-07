package com.wei;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringmanageApplicationTests {

    @Test
    @DisplayName("对登陆进行测试")
    void loginTest(@Autowired MockMvc mvc) throws Exception {

        //登录成功
        mvc.perform(MockMvcRequestBuilders
                .post("/toLogin")
                .param("username", "root")
                .param("password", "123"))
                .andExpect(request().sessionAttributeDoesNotExist("SPRING_SECURITY_LAST_EXCEPTION")).andDo(print());

        //登录失败
        mvc.perform(MockMvcRequestBuilders
                .post("/toLogin")
                .param("username", "root")
                .param("password", "1233"))
                .andExpect(request().sessionAttributeDoesNotExist("SPRING_SECURITY_CONTEXT")).andDo(print());
    }
    
    
    /**
     * 使用测试环境h2数据库y
     * @param args
     */
    public static void main(String[] args) {
        SpringmanageApplication.main(args);
    }

}
