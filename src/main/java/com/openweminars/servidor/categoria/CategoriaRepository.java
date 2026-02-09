package com.openweminars.servidor.categoria;

import com.openweminars.servidor.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    List<Categoria> findByIdBetween(int num1, int num2);

    Categoria findByNombre(String nombre);

}
