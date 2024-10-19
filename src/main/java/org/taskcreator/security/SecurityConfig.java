package org.taskcreator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности для приложения TaskCreator.
 * <p>
 * Этот класс настраивает безопасность веб-приложения, включая аутентификацию и авторизацию пользователей.
 * Основной механизм аутентификации основан на памяти. Также определены правила доступа к ресурсам.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Настраивает фильтр безопасности для HTTP.
     *
     * @param http объект HttpSecurity для настройки безопасности.
     * @return настроенный объект SecurityFilterChain.
     * @throws Exception в случае ошибки конфигурации.
     */
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

    /**
     * Создает экземпляр UserDetailsService для хранения пользователей в памяти.
     *
     * @return UserDetailsService с заранее определенными пользователями и их ролями.
     * @throws Exception в случае ошибки конфигурации.
     */
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Создание пользователей с именем, паролем и ролями
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER") // Роль пользователя
                .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN") // Роль администратора
                .build());
        return manager;
    }
}
