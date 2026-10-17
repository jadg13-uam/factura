package com.example.factura.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "etiqueta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToMany(mappedBy = "etiquetas")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Producto> productos = new HashSet<>();
}