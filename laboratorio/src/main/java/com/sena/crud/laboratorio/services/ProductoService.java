package com.sena.crud.laboratorio.services;

import java.util.ArrayList;
import java.util.List;

import com.sena.crud.laboratorio.dto.ProductoCreateDto;
import com.sena.crud.laboratorio.dto.ProductoUpdateDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud.laboratorio.dto.ProductoGetDto;
import com.sena.crud.laboratorio.mapping.Mapper;
import com.sena.crud.laboratorio.model.Producto;
import com.sena.crud.laboratorio.repository.ProductoRepository;

@Service
public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
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

    public boolean actualizarProducto(ProductoUpdateDto producto) {
        boolean isUpdated = false;

        Producto productoEncontrado = productoRepository.findById(producto.id()).orElse(null);

        if (productoEncontrado != null){
            productoEncontrado.setCodigo(producto.codigo());
            productoEncontrado.setNombreProducto(producto.nombreProducto());
            productoEncontrado.setPrecio(producto.precio());
            productoEncontrado.setStock(producto.stock());
            productoEncontrado.setDescripcion(producto.descripcion());
            isUpdated = true;
        }

        assert productoEncontrado != null;
        productoRepository.save(productoEncontrado);

        return isUpdated;
    }

    public boolean eliminarProducto(int id){
        boolean isDelete = false;

        Producto productoEncontrado = productoRepository.findById(id).orElse(null);

        if (productoEncontrado != null){
            productoRepository.delete(productoEncontrado);
            isDelete = true;
        }

        return isDelete;
    }

    public void crearProducto(ProductoCreateDto producto){
        Producto productoACrear = new Producto();

        productoACrear.setCodigo(producto.codigo());
        productoACrear.setNombreProducto(producto.nombreProducto());
        productoACrear.setDescripcion(producto.descripcion());
        productoACrear.setPrecio(producto.precio());
        productoACrear.setEstado(producto.estado());
        productoACrear.setStock(producto.stock());

        productoRepository.save(productoACrear);

    }

}
