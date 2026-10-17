package com.example.factura.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoRequestDTO {

    private String codigo;

    private String nombre;

    private BigDecimal precioVenta;

    private Integer existencia;

    private Integer categoriaId;
}