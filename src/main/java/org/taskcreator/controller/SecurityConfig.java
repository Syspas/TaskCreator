package org.taskcreator.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Позволить доступ всем пользователям, никаких ограничений
        http.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Разрешаем всем доступ ко всем запросам
                )
                .csrf(csrf -> csrf.disable()); // Отключаем CSRF для упрощения (не рекомендуется для продакшена)

        return http.build();
    }
}
