package com.example.factura.services;

import com.example.factura.dto.EtiquetaRequestDTO;
import com.example.factura.entities.Etiqueta;
import com.example.factura.repositories.EtiquetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaService {

    private final EtiquetaRepository repositorio;

    public EtiquetaService(EtiquetaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Etiqueta> listar() {
        return repositorio.findAll();
    }

    public Etiqueta buscarPorId(Integer id) {
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Etiqueta no encontrada con id: " + id
                        ));
    }

    public Etiqueta save(EtiquetaRequestDTO dto) {

        Etiqueta etiqueta = new Etiqueta();

        etiqueta.setNombre(dto.getNombre());

        return repositorio.save(etiqueta);
    }

    public Etiqueta actualizar(
            Integer id,
            EtiquetaRequestDTO dto) {

        Etiqueta etiqueta = buscarPorId(id);

        etiqueta.setNombre(dto.getNombre());

        return repositorio.save(etiqueta);
    }

    public boolean delete(Integer id) {

        if (!repositorio.existsById(id)) {
            return false;
        }

        repositorio.deleteById(id);

        return true;
    }
}