package com.openweminars.servidor.producto;

import com.openweminars.servidor.categoria.Categoria;
import com.openweminars.servidor.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private final ProductoService productoService;
    @Autowired
    private final CategoriaService categoriaService;
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService,
                              ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.productoRepository = productoRepository;
    }

    @GetMapping("/lista")
    public String listaProductos (Model model){
        model.addAttribute("productos",productoService.obtenerProductos());
        return "paginaProductos";
    }
    @GetMapping("/{id:[0-9]+}")
    public String productoConcreto (@PathVariable("id") String id,Model model){
        Long idLong = Long.parseLong(id);
        Producto producto = productoService.productoConcreto(idLong);
        if (producto == null) {
            model.addAttribute("mensaje","El producto con ID:" + id + " no existe");
            return "error/404";
        }
        model.addAttribute("producto",producto);

        return "productoConcreto";
    }
    @GetMapping("/param")
    public String param(@RequestParam(name="nombre",required = false,defaultValue = "user") String nombre,@RequestParam(name="edad",required = false,defaultValue = "0") int edad,Model model){
        model.addAttribute("nombre",nombre);
        model.addAttribute("edad",edad);
        return "param";
    }
    @ModelAttribute("categorias")
    public List<Categoria> categorias(){
        return categoriaService.listaCategorias();
    }
    @GetMapping ("/nuevo")
    public String nuevoProducto(Model model){
        Producto producto = new Producto();
        producto.setCategoria(new Categoria()); // <<< inicializamos para evitar null
        model.addAttribute("producto",producto);
        return "form-producto";
    }
    @PostMapping("/nuevo")
    public String procesarFormulario(@ModelAttribute("producto") Producto producto, Model model){
        // Log del producto recibido
        System.out.println("Producto recibido del formulario: " + producto);
        if (producto.getCategoria() != null) {
            System.out.println("ID de categoría recibido: " + producto.getCategoria().getId());
        } else {
            System.out.println("No se recibió categoría");
        }

        // Obtener la categoría real de la base de datos usando el id del formulario
        Categoria cat = categoriaService.obtenerCategoria(producto.getCategoria().getId());
        if (cat == null) {
            System.out.println("No se encontró la categoría con id: " + producto.getCategoria().getId());
            model.addAttribute("mensaje", "La categoría seleccionada no existe");
            model.addAttribute("producto", producto);
            return "form-producto";
        }

        System.out.println("Categoría encontrada en DB: " + cat);
        producto.setCategoria(cat); // Asignar la categoría real

        productoService.guardar(producto);
        System.out.println("Producto guardado correctamente: " + producto);

        return "redirect:/productos/lista";
    }
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id,Model model ){
        Producto producto= productoService.productoConcreto(id);
        model.addAttribute("producto",producto);
        return "form-producto";
    }
    @GetMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable Long id, Model model){
        productoService.borrarProducto(id);
        return "redirect:/productos/lista";
    }
}
