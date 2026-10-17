package com.example.factura.controllers;

import com.example.factura.dto.CategoriaRequestDTO;
import com.example.factura.entities.Categoria;
import com.example.factura.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(
            @PathVariable Integer id) {

        Categoria categoria = categoriaService.findById(id);

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<?> guardar(
            @RequestBody CategoriaRequestDTO dto) {

        Categoria nuevaCategoria =
                categoriaService.save(dto);

        return ResponseEntity.ok(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Integer id,
            @RequestBody CategoriaRequestDTO dto) {

        Categoria categoria =
                categoriaService.actualizar(id, dto);

        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        boolean eliminado =
                categoriaService.delete(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}