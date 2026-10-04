package com.example.factura.controllers;

import com.example.factura.entities.Categoria;
import com.example.factura.repositories.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Categoria> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Categoria categoria) {
        if (repository.existsByNombreIgnoreCase(categoria.getNombre())) {
            Map<String, Object>  response = new HashMap<>();
            response.put("mensaje", "El categoria existe");
            response.put("nombre categoria", categoria.getNombre());
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
        Categoria nuevaCategoria =  repository.save(categoria);
        return ResponseEntity.ok(nuevaCategoria);
    }


}
