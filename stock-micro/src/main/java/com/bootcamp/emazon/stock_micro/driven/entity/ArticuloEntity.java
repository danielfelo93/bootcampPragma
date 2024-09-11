package com.bootcamp.emazonapi.driven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table(name = "articulo")
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 50)
        private String nombre;

        @Column(nullable = false, length = 120)
        private String descripcion;

        @Column(nullable = false)
        private int cantidad;

        @Column(nullable = false)
        private BigDecimal precio;

        @ManyToOne
        @JoinColumn(name = "marca_id")
        private MarcaEntity marca;

        @ManyToMany
        @JoinTable(
                name = "articulo_categoria",
                joinColumns = @JoinColumn(name = "articulo_id"),
                inverseJoinColumns = @JoinColumn(name = "categoria_id")
        )
        private Set<CategoriaEntity> categorias;
}

