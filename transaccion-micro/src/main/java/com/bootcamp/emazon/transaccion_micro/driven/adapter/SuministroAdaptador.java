package com.bootcamp.emazon.transaccion_micro.driven.adapter;

import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.domain.spi.ISuministroPersistencePort;
import com.bootcamp.emazon.transaccion_micro.driven.entity.SuministroEntity;
import com.bootcamp.emazon.transaccion_micro.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazon.transaccion_micro.driven.mapper.ISuministroEntityMapper;
import com.bootcamp.emazon.transaccion_micro.driven.repository.ISuministroRepository;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SuministroAdaptador implements ISuministroPersistencePort {

    private final ISuministroRepository suministroRepository;
    private final ISuministroEntityMapper suministroEntityMapper;


    private Pageable getPageable(int page, int size, String order) {
        if ("desc".equalsIgnoreCase(order)) {
            return PageRequest.of(page, size, Sort.by("fechaSuministro").descending());
        } else if ("asc".equalsIgnoreCase(order)) {
            return PageRequest.of(page, size, Sort.by("fechaSuministro").ascending());
        } else {
            return PageRequest.of(page, size, Sort.by("id"));
        }
    }

    @Override
    public Suministro guardarSuministro(Suministro suministro) {
        Optional<SuministroEntity> suministroExistente = suministroRepository.findByArticuloId(suministro.getArticuloId());

        SuministroEntity suministroEntityActualizado;

        if (suministroExistente.isPresent()) {
            suministroEntityActualizado = suministroExistente.get();
            suministroEntityActualizado.setCantidad(suministro.getCantidad());
        } else {
            suministroEntityActualizado = suministroEntityMapper.toEntity(suministro);
        }
        suministroRepository.save(suministroEntityActualizado);
        return suministroEntityMapper.toSuministro(suministroEntityActualizado);
    }

    @Override
    public Optional<Suministro> obtenerSuministroPorArticuloId(Long articuloId) {
        return suministroRepository.findByArticuloId(articuloId)
                .map(suministroEntityMapper::toSuministro);
    }

    @Override
    public PagedResponse<Suministro> obtenerTodosSuministros(int page, int size, String order) {
        Pageable pageable = getPageable(page, size, order);
        Page<SuministroEntity> suministroPage = suministroRepository.findAll(pageable);
        List<SuministroEntity> suministros = suministroPage.getContent();

        if (suministros.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Mapea las entidades a objetos de dominio Articulo
        List<Suministro> suministroList = suministroEntityMapper.suministroEntityToSuministroList(suministros);

        return new PagedResponse<>(
                suministroList,
                suministroPage.getNumber(),
                suministroPage.getSize(),
                suministroPage.getTotalElements(),
                suministroPage.getTotalPages(),
                suministroPage.isLast()
        );
    }

}
