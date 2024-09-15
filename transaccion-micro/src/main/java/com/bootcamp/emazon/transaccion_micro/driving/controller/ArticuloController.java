package com.bootcamp.emazon.transaccion_micro.driving.controller;

import com.bootcamp.emazon.transaccion_micro.domain.api.IArticuloServicePort;
import com.bootcamp.emazon.transaccion_micro.domain.service.Articulo;
import com.bootcamp.emazon.transaccion_micro.driving.dto.request.AddArticuloRequest;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.ArticuloResponse;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.transaccion_micro.driving.mapper.IArticuloRequestMapper;
import com.bootcamp.emazon.transaccion_micro.driving.mapper.IArticuloResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulos2")
@RequiredArgsConstructor
public class ArticuloController {

    private final IArticuloServicePort articuloServicePort;
    private final IArticuloRequestMapper articuloRequestMapper;
    private final IArticuloResponseMapper articuloResponseMapper;

    @Operation(summary = "Agregar un nuevo artículo", description = "Crea un nuevo artículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/admin/")
    public ResponseEntity<Void> crearArticulo(@RequestBody AddArticuloRequest request) {

        // Mapear el request a la entidad Articulo sin asociar aún la marca ni las categorías
        Articulo articulo = articuloRequestMapper.addRequestToArticulo(request);

        // Delegar la lógica de guardar artículo y asociar marca y categorías al puerto de servicio
        articuloServicePort.guardarArticulo(articulo);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Obtener todos las articulos", description = "Devuelve una lista de todos los articulos paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articuloss obtenidos con éxito"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping("/")
    public ResponseEntity<PagedResponse<ArticuloResponse>> obtenerArticulos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                            @RequestParam(value = "size", defaultValue = "3") int size,
                                                                            @RequestParam(value = "order", defaultValue = "") String order) {

        // Llamar al use case para obtener los artículos
        PagedResponse<Articulo> articulos = articuloServicePort.listarArticulos(page, size, order);

        // Usar el mapper para convertir los artículos a ArticuloResponse
        PagedResponse<ArticuloResponse> response = articuloResponseMapper.articuloToResponseList(articulos);

        return ResponseEntity.ok(response);

    }

}


