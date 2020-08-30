package com.hello.user.bday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BdayApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BdayApplication.class, args);
    }

}
