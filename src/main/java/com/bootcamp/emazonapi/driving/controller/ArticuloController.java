package com.bootcamp.emazonapi.driving.controller;

import com.bootcamp.emazonapi.domain.api.IArticuloServicePort;
import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.driving.dto.request.AddArticuloRequest;
import com.bootcamp.emazonapi.driving.dto.response.*;
import com.bootcamp.emazonapi.driving.mapper.IArticuloRequestMapper;
import com.bootcamp.emazonapi.driving.mapper.IArticuloResponseMapper;
import com.bootcamp.emazonapi.driving.mapper.IMarcaResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articulos")
@RequiredArgsConstructor
public class ArticuloController {
    
    private final IArticuloServicePort articuloServicePort;
    private final IArticuloRequestMapper articuloRequestMapper;
    private final IArticuloResponseMapper articuloResponseMapper;
    private final IMarcaResponseMapper marcaResponseMapper;

    @Operation(summary = "Agregar un nuevo artículo", description = "Crea un nuevo artículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<Void> crearArticulo(@RequestBody AddArticuloRequest request) {

        // Validación de las categorías
        List<Long> categoriaIds = request.getCategoriaIds();
        if (categoriaIds == null || categoriaIds.isEmpty() || categoriaIds.size() > 3) {
            throw new IllegalArgumentException("El artículo debe tener entre 1 y 3 categorías.");
        }

        // Validación de duplicados en las categorías
        List<Long> uniqueCategoriaIds = new ArrayList<>(categoriaIds);
        if (uniqueCategoriaIds.size() != categoriaIds.size()) {
            throw new IllegalArgumentException("No se permiten categorías duplicadas.");
        }

        // Mapear el request a la entidad Articulo sin asociar aún la marca ni las categorías
        Articulo articulo = articuloRequestMapper.addRequestToArticulo(request);


        // Delegar la lógica de guardar artículo y asociar marca y categorías al puerto de servicio
        articuloServicePort.guardarArticulo(articulo, request.getMarcaId(), request.getCategoriaIds());


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Obtener todos las articulos", description = "Devuelve una lista de todos los articulos paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articuloss obtenidos con éxito"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping("/")
    public ResponseEntity<List<ArticuloResponse>> obtenerArticulos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                     @RequestParam(value = "size", defaultValue = "3") int size,
                                                                     @RequestParam(value = "order", defaultValue = "") String order) {

        // Llamar al use case para obtener los artículos
        List<Articulo> articulos = articuloServicePort.listarArticulos(page, size, order);

        // Convertir a DTO excluyendo la descripción
        List<ArticuloResponse> response = articulos.stream()
                .map(articulo -> new ArticuloResponse(
                        articulo.getId(),
                        articulo.getNombre(),
                        articulo.getDescripcion(),
                        articulo.getCantidad(),
                        articulo.getPrecio(),
                        // Mapea MarcaResponse excluyendo la descripción
                        new ArticuloMarcaResponse(articulo.getMarca().getId(), articulo.getMarca().getNombre()),
                        // Mapea CategoriaResponse excluyendo la descripción
                        articulo.getCategorias().stream()
                                .map(categoria -> new ArticuloCategoriaResponse(categoria.getId(), categoria.getNombre()))
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);


        }

}


