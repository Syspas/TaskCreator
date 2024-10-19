package org.taskcreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("1", "1");
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    @GetMapping("/")
    public String index(@SessionAttribute(name = "username", required = false) String username, Model model) {
        if (username != null) {
            model.addAttribute("username", username);
            return "index";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        @SessionAttribute(name = "username", required = false) String currentUsername) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            model.addAttribute("username", username);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(@SessionAttribute(name = "username", required = false) String username, Model model) {
        if (username != null) {
            model.addAttribute("username", username);
        }
        return "redirect:/";
    }
}
