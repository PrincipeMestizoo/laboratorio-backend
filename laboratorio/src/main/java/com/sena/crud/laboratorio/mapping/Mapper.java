package com.sena.crud.laboratorio.mapping;

import com.sena.crud.laboratorio.dto.ProductoGetDto;
import com.sena.crud.laboratorio.model.Producto;

public class Mapper {

    public static ProductoGetDto ProductoToProductoGetDto(Producto producto) {
        ProductoGetDto productoGetDto = new ProductoGetDto(
                producto.getCodigo(),
                producto.getNombreProducto(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getEstado());

        return productoGetDto;
    }

}
