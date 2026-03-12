package com.openweminars.servidor.service;

import com.openweminars.servidor.exceptions.CategoriaException;
import com.openweminars.servidor.model.Categoria;
import com.openweminars.servidor.model.Producto;
import com.openweminars.servidor.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria>listaCategorias(){
        return categoriaRepository.findAll();
    }
    public Categoria obtenerCategoria(Long id){
        return categoriaRepository.findById(id).orElse(null);
    }
    public Categoria guardarCategoria(Categoria categoria){

        Optional<Categoria> existente = categoriaRepository.findByNombreIgnoreCase(categoria.getNombre());

        if(existente.isPresent() && !existente.get().getId().equals(categoria.getId())){
            throw new CategoriaException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }

        return categoriaRepository.save(categoria);
    }
    @Transactional
    public void borrarCategoria(Long id){

        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        // Validar que no se edite General
        if (categoria.getId() != null) { // es edición
            Categoria existente = categoriaRepository.findById(categoria.getId())
                    .orElseThrow(() -> new CategoriaException("Categoría no encontrada"));

            if (existente.getNombre().equals("General") && !categoria.getNombre().equals("General")) {
                throw new CategoriaException("No se puede editar la categoría General");
            }
        }
        if(categoria.getNombre().equals("General")){
            throw new CategoriaException("No se puede borrar la categoría General");
        }

        Categoria general = categoriaRepository.findByNombre("General");

        for(Producto producto : categoria.getProductos()){
            producto.setCategoria(general);
        }

        categoriaRepository.delete(categoria);
    }
}
