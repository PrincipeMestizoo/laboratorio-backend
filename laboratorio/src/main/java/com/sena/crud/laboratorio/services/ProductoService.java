package com.sena.crud.laboratorio.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.crud.laboratorio.dto.ProductoGetDto;
import com.sena.crud.laboratorio.mapping.Mapper;
import com.sena.crud.laboratorio.model.Producto;
import com.sena.crud.laboratorio.repository.ProductoRepository;

@Service
public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        productoRepository = productoRepository;
    }

    public ProductoGetDto obtenerProductoPorId(int id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        ProductoGetDto productoGetDto = Mapper.ProductoToProductoGetDto(producto);

        return productoGetDto;

    }

    public ArrayList<ProductoGetDto> obtenerProductos() {
        ArrayList<ProductoGetDto> productos = new ArrayList<>();

        List<Producto> listaProductos = productoRepository.findAll();

        for (Producto p : listaProductos) {
            ProductoGetDto producto = Mapper.ProductoToProductoGetDto(p);
            productos.add(producto);
        }

        return productos;
    }

}
