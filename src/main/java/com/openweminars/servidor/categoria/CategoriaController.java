package com.openweminars.servidor.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/lista")
    public String listaCategorias(Model model){
        model.addAttribute("categorias",categoriaService.listaCategorias());
        return "categorias";
     }
     @GetMapping("/{id:[0-9]+}")
    public String categoiraConcreta(@PathVariable("id") String id,Model model){
        Long idLong = Long.parseLong(id);
        model.addAttribute("categoria",categoriaService.obtenerCategoria(idLong));
        return "categoria";
     }
     @GetMapping("/{nombre}")
    public String categoriaNombre(@PathVariable("nombre") String nombre,Model model){
        model.addAttribute("categoria",categoriaService.nombreCategoria(nombre));
        return "nombreCategoria";
     }
}
