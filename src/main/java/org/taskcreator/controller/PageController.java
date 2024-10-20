package org.taskcreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login"; // имя представления для страницы логина
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // имя представления для домашней страницы
    }


    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout-success"; // имя представления для страницы завершения сеанса
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout-success"; // имя представления для страницы завершения сеанса
    }


}
