package com.openweminars.servidor.controller;

import com.openweminars.servidor.model.Usuario;
import com.openweminars.servidor.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    // LISTAR TODOS LOS USUARIOS
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        log.info("Listando los usuarios ");
        return "usuarios";
    }

    // FORMULARIO PARA CREAR NUEVO USUARIO
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "form-usuario"; // form-usuario.html
    }



    // GUARDAR USUARIO NUEVO
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        log.info("Creando el usuario "+ usuario.getUsername());
        return "redirect:/usuarios";
    }

    // FORMULARIO PARA EDITAR USUARIO
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "form-usuario"; // form-usuario.html reutilizado
        }
        log.info("Editando usuario con id: "+ id);
        return "redirect:/usuarios";
    }

    // BORRAR USUARIO
    @GetMapping("/borrar/{id}")
    public String borrarUsuario(@PathVariable Long id) {
        usuarioService.borrarUsuario(id);
        log.warn("Intento de borrar usuario con id: "+ id);
        return "redirect:/usuarios";
    }
}

