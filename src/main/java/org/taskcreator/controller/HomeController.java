package org.taskcreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@SessionAttribute(name = "username", required = false) String username, Model model) {
        if (username != null) {
            model.addAttribute("username", username);
            return "home";
        }
        return "redirect:/";
    }
}
