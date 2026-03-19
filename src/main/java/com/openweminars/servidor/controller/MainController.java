package com.openweminars.servidor.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {
        return "login"; // plantilla login personalizada
    }

    @GetMapping("/")
    public String index() {
        return "index"; // página de inicio
    }


}
