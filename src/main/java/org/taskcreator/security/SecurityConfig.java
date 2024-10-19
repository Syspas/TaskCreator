package org.taskcreator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/static/**").permitAll() // Разрешение доступа без аутентификации
                        .anyRequest().authenticated() // Требуется аутентификация для всех остальных запросов
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Страница логина
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Страница после выхода
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Временное отключение для разработки; включите в производстве!

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Create users with username, password, and roles
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER") // User role
                .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN") // Admin role
                .build());
        return manager;
    }
}