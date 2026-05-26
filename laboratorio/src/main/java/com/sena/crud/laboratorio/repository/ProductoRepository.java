package com.sena.crud.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud.laboratorio.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
