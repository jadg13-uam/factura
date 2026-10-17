package com.example.factura.controllers;

import com.example.factura.dto.ProductoRequestDTO;
import com.example.factura.entities.Producto;
import com.example.factura.services.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(
            @PathVariable Integer id) {

        Producto producto =
                productoService.buscarPorId(id);

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(
            @RequestBody ProductoRequestDTO dto) {

        Producto producto =
                productoService.guardar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Integer id,
            @RequestBody ProductoRequestDTO dto) {

        Producto producto =
                productoService.actualizar(id, dto);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        boolean eliminado =
                productoService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> listarPorCategoria(
            @PathVariable Integer categoriaId) {

        List<Producto> productos =
                productoService.listarPorCategoria(categoriaId);

        return ResponseEntity.ok(productos);
    }

    @PostMapping("/{productoId}/etiquetas/{etiquetaId}")
    public ResponseEntity<Producto> agregarEtiqueta(
            @PathVariable Integer productoId,
            @PathVariable Integer etiquetaId) {

        Producto producto =
                productoService.agregarEtiqueta(
                        productoId,
                        etiquetaId
                );

        return ResponseEntity.ok(producto);
    }
}