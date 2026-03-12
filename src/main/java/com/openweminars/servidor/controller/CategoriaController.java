package com.openweminars.servidor.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.openweminars.servidor.model.Categoria;
import com.openweminars.servidor.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);
    @GetMapping("/lista")
    public String listaCategorias(Model model){
        model.addAttribute("categorias",categoriaService.listaCategorias());
        log.info("Listando las categorías ");
        return "categorias";
    }
    @GetMapping("/{id}")
    public String categoiraConcreta(@PathVariable Long id, Model model){
        Categoria categoria = categoriaService.obtenerCategoria(id);
        model.addAttribute("categoria", categoria);
        log.info("Buscando categoría con id: "+ id);
        return "categoria";
    }

    @GetMapping ("/nuevo")
    public String nuevaCategoria(Model model){
        Categoria categoria = new Categoria();
        model.addAttribute("categoria",categoria);
        log.info("Creando la categoría "+ categoria.getNombre());
        return "form-categoria";
    }
    @PostMapping ("/nuevo")
    public String categoriaNueva(@ModelAttribute("categoria") Categoria categoria, Model model){
        categoriaService.guardarCategoria(categoria);
        log.info("Categoría guardada correctamente: " + categoria.getNombre());

        return "redirect:/categorias/lista";
    }
    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id,Model model ){
        Categoria categoria= categoriaService.obtenerCategoria(id);
        if(categoria.getNombre().equalsIgnoreCase("General")) {
            model.addAttribute("mensajeError", "No se puede editar la categoría General");
            log.warn("La categoría General no se puede borrar");
            return "error/error"; // o una vista de error
        }
        model.addAttribute("categoria",categoria);
        return "form-categoria";
    }
    @GetMapping("/borrar/{id}")
    public String borrarCategoria(@PathVariable Long id, Model model){
        categoriaService.borrarCategoria(id);
        log.warn("Intento de borrado de la categoria con Id: "+ id);
        return "redirect:/categorias/lista";
    }
}
