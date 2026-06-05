package com.sena.crud.laboratorio.controller;

import com.sena.crud.laboratorio.dto.ProductoCreateDto;
import com.sena.crud.laboratorio.dto.ProductoGetDto;
import com.sena.crud.laboratorio.dto.ProductoUpdateDto;
import com.sena.crud.laboratorio.services.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET api/productos
    @GetMapping
    public ResponseEntity<ArrayList<ProductoGetDto>> obtenerProductos() {
        ArrayList<ProductoGetDto> productos = productoService.obtenerProductos();
        return ResponseEntity.ok(productos);
    }

    // GET api/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductoGetDto> obtenerProducto(@PathVariable int id) {
        ProductoGetDto producto = productoService.obtenerProductoPorId(id);

        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    // POST api/productos
    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody ProductoCreateDto productoCreateDto) {
        productoService.crearProducto(productoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado exitosamente");
    }

    // PUT api/productos
    @PutMapping
    public ResponseEntity<String> actualizarProducto(@RequestBody ProductoUpdateDto productoUpdateDto) {
        boolean actualizado = productoService.actualizarProducto(productoUpdateDto);

        if (!actualizado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }

        return ResponseEntity.ok("Producto actualizado exitosamente");
    }

    // DELETE api/productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        boolean eliminado = productoService.eliminarProducto(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }

        return ResponseEntity.ok("Producto eliminado exitosamente");
    }
}