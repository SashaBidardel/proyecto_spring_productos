package com.openweminars.servidor.categoria;

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
    public List<Categoria >listaCategorias(){
        return categoriaRepository.findAll();
    }
    public Categoria obtenerCategoria(Long id){
        return categoriaRepository.findById(id).orElse(null);
    }
    public List<Categoria> categoriaMayor(int num1,int num2){
        return categoriaRepository.findByIdBetween(num1,num2);
    }
    public Categoria nombreCategoria(String nombre){
        return categoriaRepository.findByNombre(nombre);
    }
    }
