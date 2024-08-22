package com.bootcamp.emazonAPI.driving.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.emazonAPI.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonAPI.driving.dto.request.AddCategoriaRequest;
import com.bootcamp.emazonAPI.driving.dto.response.CategoriaResponse;
import com.bootcamp.emazonAPI.driving.mapper.ICategoriaRequestMapper;
import com.bootcamp.emazonAPI.driving.mapper.ICategoriaResponseMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    
    private final ICategoriaServicePort categoriaServicePort;
    private final ICategoriaRequestMapper categoriaRequestMapper;
    private final ICategoriaResponseMapper categoriaResponseMapper;

    
    @PostMapping
    public ResponseEntity<Void> crearCategoria(@RequestBody AddCategoriaRequest request) {
        categoriaServicePort.guardarCategoria(categoriaRequestMapper.addRequestToCategoria(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/")
    public ResponseEntity<List<CategoriaResponse>> obtenerCategorias(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(categoriaResponseMapper.CategoriaToResponseList(categoriaServicePort.listarCategorias(page, size)));
        }

    @GetMapping("/nopage")
    public ResponseEntity<List<CategoriaResponse>> obtenerCategorias() {
        return ResponseEntity.ok(categoriaResponseMapper.CategoriaToResponseList(categoriaServicePort.listarCategoriasfree()));
        }
}