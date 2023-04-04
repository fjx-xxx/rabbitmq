package com.example.springbootoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SpringbootOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOauth2Application.class, args);
    }

}
