package org.example.endtermproject;

import org.example.endtermproject.patterns.DbConfigSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class EndtermProjectApplication {

    @Value("${spring.datasource.url}") private String url;
    @Value("${spring.datasource.username}") private String user;
    @Value("${spring.datasource.password}") private String password;

    @PostConstruct
    public void init() {
        DbConfigSingleton.initOnce(url, user, password);
    }

    public static void main(String[] args) {
        SpringApplication.run(EndtermProjectApplication.class, args);
    }
}
