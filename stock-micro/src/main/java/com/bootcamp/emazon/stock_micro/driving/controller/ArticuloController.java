package com.bootcamp.emazon.stock_micro.driving.controller;

import com.bootcamp.emazon.stock_micro.domain.api.IArticuloServicePort;
import com.bootcamp.emazon.stock_micro.domain.exception.DuplicatedFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.LimitExceededException;
import com.bootcamp.emazon.stock_micro.domain.service.Articulo;
import com.bootcamp.emazon.stock_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.stock_micro.driving.dto.request.AddArticuloRequest;
import com.bootcamp.emazon.stock_micro.driving.dto.request.SuministroRequest;
import com.bootcamp.emazon.stock_micro.driving.dto.response.ArticuloResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.stock_micro.driving.mapper.IArticuloRequestMapper;
import com.bootcamp.emazon.stock_micro.driving.mapper.IArticuloResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/articulos")
@RequiredArgsConstructor
public class ArticuloController {

    private static final Logger logger = LoggerFactory.getLogger(ArticuloController.class);
    private final IArticuloServicePort articuloServicePort;
    private final IArticuloRequestMapper articuloRequestMapper;
    private final IArticuloResponseMapper articuloResponseMapper;

    @Operation(summary = "Agregar un nuevo artículo", description = "Crea un nuevo artículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/admin")
    public ResponseEntity<Void> crearArticulo(@RequestBody AddArticuloRequest request) {

        // Validación de las categorías
        List<Long> categoriaIds = request.getCategoriaIds();
        if (categoriaIds == null || categoriaIds.isEmpty() || categoriaIds.size() > 3) {
            throw new LimitExceededException(ConstantesDominio.CAMPO_CATEGORIA_TAMANO_EXCEDIDO_MENSAJE);
        }

        // Validación de duplicados en las categorías
        List<Long> uniqueCategoriaIds = new ArrayList<>(categoriaIds);
        if (uniqueCategoriaIds.size() != categoriaIds.size()) {
            throw new DuplicatedFieldException(ConstantesDominio.CAMPO_CATEGORIA_DUPLICADA_MENSAJE);
        }

        // Mapear el request a la entidad Articulo sin asociar aún la marca ni las categorías
        Articulo articulo = articuloRequestMapper.addRequestToArticulo(request);


        // Delegar la lógica de guardar artículo y asociar marca y categorías al puerto de servicio
        articuloServicePort.crearArticulo(articulo, request.getMarcaId(), request.getCategoriaIds());


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Agregar suministros a un artículo", description = "Incrementa la cantidad de un artículo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suministros agregados con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PutMapping("/aux_bodega")
    public ResponseEntity<Void> agregarArticulos(@RequestBody SuministroRequest request) {
        try {

            long articuloId = request.getArticuloId();
            int cantidad = request.getCantidad();
            logger.info("Artículo ID: {} Cantidad: {}", articuloId, cantidad);

            // Llamar al use case para agregar el suministro
            articuloServicePort.agregarArticulos(articuloId, cantidad);
            return ResponseEntity.ok().build();
            // Lógica para agregar o actualizar el artículo
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artículo no encontrado", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado", e);
        }

    }


    @Operation(summary = "Obtener todos los articulos", description = "Devuelve una lista de todos los articulos paginados")
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

    @Operation(summary = "Obtener artículos por nombre de categoría", description = "Devuelve una lista de artículos filtrados por nombre de categoría paginados")
    @GetMapping("/por-categoria")
    public ResponseEntity<PagedResponse<ArticuloResponse>> obtenerArticulosPorCategoria(
            @RequestParam(value = "nombreCategoria") String nombreCategoria,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "order", defaultValue = "") String order) {

        PagedResponse<Articulo> articulos = articuloServicePort.listarArticulosPorCategoria(nombreCategoria, page, size, order);
        PagedResponse<ArticuloResponse> response = articuloResponseMapper.articuloToResponseList(articulos);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener artículos por nombre de marca", description = "Devuelve una lista de artículos filtrados por nombre de marca paginados")
    @GetMapping("/por-marca")
    public ResponseEntity<PagedResponse<ArticuloResponse>> obtenerArticulosPorMarca(
            @RequestParam(value = "nombreMarca") String nombreMarca,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "order", defaultValue = "") String order) {

        PagedResponse<Articulo> articulos = articuloServicePort.listarArticulosPorMarca(nombreMarca, page, size, order);
        PagedResponse<ArticuloResponse> response = articuloResponseMapper.articuloToResponseList(articulos);
        return ResponseEntity.ok(response);
    }

}


