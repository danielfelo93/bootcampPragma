package com.bootcamp.emazon.stock_micro.driving.controller;

import com.bootcamp.emazon.stock_micro.domain.api.IMarcaServicePort;
import com.bootcamp.emazon.stock_micro.driving.dto.request.AddMarcaRequest;
import com.bootcamp.emazon.stock_micro.driving.dto.response.MarcaResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.stock_micro.driving.mapper.IMarcaRequestMapper;
import com.bootcamp.emazon.stock_micro.driving.mapper.IMarcaResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marcas")
@RequiredArgsConstructor
public class MarcaController {
    
    private final IMarcaServicePort marcaServicePort;
    private final IMarcaRequestMapper marcaRequestMapper;
    private final IMarcaResponseMapper marcaResponseMapper;

    @Operation(summary = "Agregar una nueva marca", description = "Crea una nueva marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })

    @PostMapping("/admin/")
    public ResponseEntity<Void> crearMarca(@RequestBody AddMarcaRequest request) {
        marcaServicePort.guardarMarca(marcaRequestMapper.addRequestToMarca(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener todas las marcas", description = "Devuelve una lista de todas las marcas paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marcas obtenidas con éxito"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping("/")
    public ResponseEntity<PagedResponse<MarcaResponse>> obtenerMarcas(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                      @RequestParam(value = "size", defaultValue = "3") int size,
                                                                      @RequestParam(value = "order", defaultValue = "") String order) {
        return ResponseEntity.ok(marcaResponseMapper.marcaToResponseList(marcaServicePort.listarMarcas(page, size, order)));
        }

}


