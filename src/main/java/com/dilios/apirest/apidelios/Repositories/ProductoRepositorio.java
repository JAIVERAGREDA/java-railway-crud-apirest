package com.dilios.apirest.apidelios.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dilios.apirest.apidelios.Entities.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

}
