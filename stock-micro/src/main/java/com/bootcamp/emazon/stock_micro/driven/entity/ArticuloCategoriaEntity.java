package com.bootcamp.emazon.stock_micro.driven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "articulo_categoria")
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloCategoriaEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "categoria_id", nullable = false)
        private CategoriaEntity categoria;

        @ManyToOne
        @JoinColumn(name = "articulo_id", nullable = false)
        private ArticuloEntity articulo;

}

