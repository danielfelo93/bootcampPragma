package com.bootcamp.emazon.transaccion_micro.driven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "suministros")
@NoArgsConstructor
@AllArgsConstructor
public class SuministroEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "articulo_id", nullable = false)
        private Long articuloId;

        @Column(name = "cantidad", nullable = false)
        private Integer cantidad;

        @Column(name = "fecha_de_ingreso", nullable = false)
        private LocalDateTime fechaDeIngreso;

}

