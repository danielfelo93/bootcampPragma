package com.bootcamp.emazon.stock_micro.driven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "marca")
@NoArgsConstructor
@AllArgsConstructor
public class MarcaEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombre;

        private String descripcion;

}

