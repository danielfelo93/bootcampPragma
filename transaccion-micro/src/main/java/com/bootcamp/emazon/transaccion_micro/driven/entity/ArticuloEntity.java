package com.bootcamp.emazon.transaccion_micro.driven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


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

}

