package com.wei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringmanageApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringmanageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).filter(e->e.contains("user")).forEach(e-> System.out.println(e));
    }
}
