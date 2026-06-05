package com.sena.crud.laboratorio.dto;

public record ProductoCreateDto(
        String codigo,
        String nombreProducto,
        Double precio,
        String estado,
        String categoria,
        Long stock,
        String descripcion
) {
}
