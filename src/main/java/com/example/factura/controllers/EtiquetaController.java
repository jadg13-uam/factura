package com.example.factura.controllers;

import com.example.factura.dto.EtiquetaRequestDTO;
import com.example.factura.entities.Etiqueta;
import com.example.factura.services.EtiquetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    private final EtiquetaService etiquetaService;

    public EtiquetaController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @GetMapping
    public List<Etiqueta> listar() {
        return etiquetaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> buscar(
            @PathVariable Integer id) {

        Etiqueta etiqueta =
                etiquetaService.buscarPorId(id);

        return ResponseEntity.ok(etiqueta);
    }

    @PostMapping
    public ResponseEntity<Etiqueta> guardar(
            @RequestBody EtiquetaRequestDTO dto) {

        Etiqueta etiqueta =
                etiquetaService.save(dto);

        return ResponseEntity.ok(etiqueta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etiqueta> actualizar(
            @PathVariable Integer id,
            @RequestBody EtiquetaRequestDTO dto) {

        Etiqueta etiqueta =
                etiquetaService.actualizar(id, dto);

        return ResponseEntity.ok(etiqueta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        boolean eliminado =
                etiquetaService.delete(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}