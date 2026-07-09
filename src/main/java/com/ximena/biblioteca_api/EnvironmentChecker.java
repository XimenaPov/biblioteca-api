package com.ximena.biblioteca_api;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentChecker {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @PostConstruct
    public void print() {
        System.out.println("===== CONFIGURACIÓN =====");
        System.out.println("URL: " + url);
        System.out.println("USER: " + user);
    }
}
