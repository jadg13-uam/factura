package com.example.factura.services;

import com.example.factura.dto.ProductoRequestDTO;
import com.example.factura.entities.Categoria;
import com.example.factura.entities.Etiqueta;
import com.example.factura.entities.Producto;
import com.example.factura.repositories.CategoriaRepository;
import com.example.factura.repositories.EtiquetaRepository;
import com.example.factura.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final EtiquetaRepository etiquetaRepository;

    public ProductoService(
            CategoriaRepository categoriaRepository,
            ProductoRepository productoRepository,
            EtiquetaRepository etiquetaRepository) {

        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Producto no encontrado con id: " + id
                        ));
    }

    public Producto guardar(ProductoRequestDTO dto) {

        Categoria categoria = categoriaRepository
                .findById(dto.getCategoriaId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Categoría no encontrada con id: "
                                        + dto.getCategoriaId()
                        ));

        Producto producto = new Producto();

        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setExistencia(dto.getExistencia());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    public Producto actualizar(
            Integer id,
            ProductoRequestDTO dto) {

        Producto producto = buscarPorId(id);

        Categoria categoria = categoriaRepository
                .findById(dto.getCategoriaId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Categoría no encontrada con id: "
                                        + dto.getCategoriaId()
                        ));

        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setExistencia(dto.getExistencia());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    public boolean eliminar(Integer id) {

        if (!productoRepository.existsById(id)) {
            return false;
        }

        productoRepository.deleteById(id);

        return true;
    }

    public List<Producto> listarPorCategoria(
            Integer categoriaId) {

        return productoRepository
                .findByCategoriaId(categoriaId);
    }

    public Producto agregarEtiqueta(
            Integer productoId,
            Integer etiquetaId) {

        Producto producto = buscarPorId(productoId);

        Etiqueta etiqueta = etiquetaRepository
                .findById(etiquetaId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Etiqueta no encontrada con id: "
                                        + etiquetaId
                        ));

        boolean yaExiste = producto.getEtiquetas()
                .stream()
                .anyMatch(e ->
                        e.getId().equals(etiquetaId)
                );

        if (!yaExiste) {
            producto.getEtiquetas().add(etiqueta);
        }

        return productoRepository.save(producto);
    }
}