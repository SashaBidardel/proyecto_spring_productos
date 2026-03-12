package com.openweminars.servidor.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {
        return "login"; // el nombre de tu plantilla login.html en templates
    }

    @GetMapping("/")
    public String index() {
        return "index"; // página de inicio
    }


}
