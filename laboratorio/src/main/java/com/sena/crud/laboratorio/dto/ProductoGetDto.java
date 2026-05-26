package com.sena.crud.laboratorio.dto;

public record ProductoGetDto(
        String codigo,
        String nombreProducto,
        String descripcion,
        Double precio,
        String estado) {

    @Override
    public String toString() {
        return "ProductoGetDto []";
    }

}
