/*
Объяснение:

1. @Controller: Аннотация, которая помечает класс как контроллер Spring MVC.
2. @GetMapping("/"): Обработчик запросов GET по адресу /. Этот метод отображает страницу index.html, если пользователь уже авторизован, и страницу login.html, если пользователь не авторизован.
3. Authentication authentication = SecurityContextHolder.getContext().getAuthentication();:  Получает объект аутентификации из контекста безопасности.
4. if (authentication != null && authentication.isAuthenticated()) {...}: Проверяет,  не равен ли объект аутентификации  null и  является ли он  аутентифицированным.
5. String username = authentication.getName();:  Извлекает имя пользователя из объекта аутентификации.
       6. model.addAttribute("username", username);:  Добавляет имя пользователя в модель для доступа в HTML-файле.
7. @GetMapping("/logout"): Обработчик запросов GET по адресу /logout. Этот метод очищает контекст безопасности и перенаправляет пользователя на главную страницу (/).

Важно:

       - SecurityConfig:  Класс  SecurityConfig  должен быть настроен с помощью Spring Security, чтобы обрабатывать аутентификацию и авторизацию пользователей.
       - HTML-файлы:  Вам нужно создать  index.html  и  login.html  файлы в папке src/main/resources/templates.
*/


package org.taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

   @GetMapping("/")
   public String index(Model model) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (authentication != null && authentication.isAuthenticated()) {
           String username = authentication.getName();
           model.addAttribute("username", username);
           return "index";
       }
       return "login";
   }

   // Добавьте этот метод, чтобы обработать logout
   @GetMapping("/logout")
   public String logout() {
       SecurityContextHolder.clearContext();
       return "redirect:/";
   }
}

