package com.bootcamp.emazon.transaccion_micro.driving.controller;

import com.bootcamp.emazon.transaccion_micro.domain.api.ISuministroServicePort;
import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.driving.dto.request.SuministroRequest;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.SuministroResponse;
import com.bootcamp.emazon.transaccion_micro.driving.mapper.ISuministroRequestMapper;
import com.bootcamp.emazon.transaccion_micro.driving.mapper.ISuministroResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suministros")
@RequiredArgsConstructor
public class SuministroController {

    private final ISuministroServicePort suministroServicePort;
    private final ISuministroRequestMapper suministroRequestMapper;
    private final ISuministroResponseMapper suministroResponseMapper;

    @Operation(summary = "Agregar nuevos suministros", description = "Agrega nuevos suministros a stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Suministros agregados con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/aux-bodega")
    public ResponseEntity<SuministroResponse> agregarSuministro(@Valid @RequestBody SuministroRequest suministroRequest) {

        Suministro suministro = suministroRequestMapper.requestToSuministro(suministroRequest);

        Suministro suministroActualizado = suministroServicePort.guardarSuministro(suministro);
        SuministroResponse suministroResponse = suministroResponseMapper.suministroToResponse(suministroActualizado);
        return ResponseEntity.status(HttpStatus.CREATED).body(suministroResponse);
    }

    @Operation(summary = "Obtener todos las suministros", description = "Devuelve una lista de todos los suministros paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suministros obtenidos con éxito"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })


    @GetMapping("/")
    public ResponseEntity<PagedResponse<SuministroResponse>> obtenerSuministros(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                            @RequestParam(value = "size", defaultValue = "3") int size,
                                                                            @RequestParam(value = "order", defaultValue = "") String order) {

        PagedResponse<Suministro> suministros = suministroServicePort.obtenerTodosSuministros(page, size, order);

        PagedResponse<SuministroResponse> response = suministroResponseMapper.suministroToResponseList(suministros);

        return ResponseEntity.ok(response);

    }

}


