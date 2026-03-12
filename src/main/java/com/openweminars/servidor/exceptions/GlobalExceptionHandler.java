package com.openweminars.servidor.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CategoriaException.class, ProductoException.class, UsuarioException.class})
    public String manejarExcepciones(RuntimeException ex, Model model){
        model.addAttribute("mensajeError", ex.getMessage());
        return "error/error";
    }
}
