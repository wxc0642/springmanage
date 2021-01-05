package com.wei;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringmanageApplicationTests {

    @Test
    void loginTest(@Autowired MockMvc mvc) throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .post("/toLogin")
                .param("username","root","password","123"))
                .andExpect(status().isOk());
    }

}
