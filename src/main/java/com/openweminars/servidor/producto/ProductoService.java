package com.openweminars.servidor.producto;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class ProductoService {
    @Autowired ProductoRepository productoRepository;
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }
    public Producto productoConcreto(Long id){
        return productoRepository.findById(id).orElse(null);
    }
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }
    public void borrarProducto(Long id){
        productoRepository.deleteById(id);
    }
}
