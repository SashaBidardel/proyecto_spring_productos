package com.openweminars.servidor;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/datos")
    public String datos(Model model){
        model.addAttribute("msg","Buenas,piratas");
        return "datos";
    }
    @GetMapping("/modeloyvista")
    public ModelAndView modeloyvista(){
        ModelAndView modelAndView= new ModelAndView("modeloyvista");
        modelAndView.addObject("mensaje","Esto es un modelo y vista a la vez");
        return modelAndView;
    }
    @ModelAttribute ("otroSaludo")
    public String otroSaludo(){
        return("Una cadena de la función otro saludo");
    }
    @ResponseBody
    @GetMapping("/textPlano")
    public String textoPlano(){
        return "Esto ye un texto plano";
    }
    @GetMapping("/estado")
    public ResponseEntity<String> respestaEstado(){
        return ResponseEntity.ok("Todo ok,piratas");
    }
}
