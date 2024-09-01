package com.bootcamp.emazonapi.driving.controller;

import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.emazonapi.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonapi.driving.dto.request.AddCategoriaRequest;
import com.bootcamp.emazonapi.driving.dto.response.CategoriaResponse;
import com.bootcamp.emazonapi.driving.mapper.ICategoriaRequestMapper;
import com.bootcamp.emazonapi.driving.mapper.ICategoriaResponseMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    
    private final ICategoriaServicePort categoriaServicePort;
    private final ICategoriaRequestMapper categoriaRequestMapper;
    private final ICategoriaResponseMapper categoriaResponseMapper;

    @Operation(summary = "Agregar una nueva categoría", description = "Crea una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<Void> crearCategoria(@RequestBody AddCategoriaRequest request) {
        categoriaServicePort.guardarCategoria(categoriaRequestMapper.addRequestToCategoria(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorías obtenidas con éxito"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping("/")
    public ResponseEntity<PagedResponse<CategoriaResponse>> obtenerCategorias(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                              @RequestParam(value = "size", defaultValue = "3") int size,
                                                                              @RequestParam(value = "order", defaultValue = "") String order) {
        return ResponseEntity.ok(categoriaResponseMapper.categoriaToResponseList(categoriaServicePort.listarCategorias(page, size, order)));
        }

}


