package com.openweminars.servidor.controller;

import com.openweminars.servidor.model.Usuario;
import com.openweminars.servidor.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar formulario de registro
    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; // tu registro.html
    }

    // Procesar formulario
    @PostMapping
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            // Guardar usuario con rol USER por defecto
            usuario.setRole("USER");
            usuarioService.guardarUsuario(usuario);
            return "redirect:/login?registrado"; // después redirige a login
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registro"; // vuelve al formulario con mensaje de error
        }
    }
}
