package com.sena.crud.laboratorio.dto;

public record ProductoUpdateDto(
        int id,
        String codigo,
        String nombreProducto,
        Double precio,
        Long stock,
        String descripcion
) {
}
