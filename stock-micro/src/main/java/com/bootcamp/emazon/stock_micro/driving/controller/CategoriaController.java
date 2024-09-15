package com.bootcamp.emazon.stock_micro.driving.controller;


import com.bootcamp.emazon.stock_micro.domain.api.ICategoriaServicePort;
import com.bootcamp.emazon.stock_micro.driving.dto.request.AddCategoriaRequest;
import com.bootcamp.emazon.stock_micro.driving.dto.response.CategoriaResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.stock_micro.driving.mapper.ICategoriaRequestMapper;
import com.bootcamp.emazon.stock_micro.driving.mapper.ICategoriaResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/admin/crear-categoria")
    public ResponseEntity<Void> crearCategoria(@RequestHeader("Authorization") String token, @RequestBody AddCategoriaRequest request) {
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


