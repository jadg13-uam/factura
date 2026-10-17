package com.example.factura.services;

import com.example.factura.dto.CategoriaRequestDTO;
import com.example.factura.entities.Categoria;
import com.example.factura.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Categoría no encontrada con id: " + id
                        ));
    }

    public Categoria save(CategoriaRequestDTO dto) {

        Categoria categoria = new Categoria();

        categoria.setNombre(dto.getNombre());
        categoria.setActiva(dto.isActiva());

        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(
            Integer id,
            CategoriaRequestDTO dto) {

        Categoria categoria = findById(id);

        categoria.setNombre(dto.getNombre());
        categoria.setActiva(dto.isActiva());

        return categoriaRepository.save(categoria);
    }

    public boolean delete(Integer id) {

        if (!categoriaRepository.existsById(id)) {
            return false;
        }

        categoriaRepository.deleteById(id);

        return true;
    }
}