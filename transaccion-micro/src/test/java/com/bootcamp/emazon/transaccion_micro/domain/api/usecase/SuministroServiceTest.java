package com.bootcamp.emazon.transaccion_micro.domain.api.usecase;

import com.bootcamp.emazon.transaccion_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.transaccion_micro.domain.exception.InvalidDataException;
import com.bootcamp.emazon.transaccion_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.domain.spi.ISuministroPersistencePort;
import com.bootcamp.emazon.transaccion_micro.driving.controller.StockClient;
import com.bootcamp.emazon.transaccion_micro.driving.dto.request.SuministroRequest;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class SuministroServiceTest {

    @Mock
    private ISuministroPersistencePort suministroPersistencePort;

    @Mock
    private StockClient stockClient;

    @InjectMocks
    private SuministroService suministroService;

    private Suministro suministro1;
    private Suministro suministro2;

    @BeforeEach
    public void setUp() {
        suministro1 = new Suministro(1L, 1L, 10); // Artículo 1, cantidad 10
        suministro2 = new Suministro(2L, 1L, 15); // Artículo 1, cantidad 15
    }

    @Test
     void testGuardarSuministro_Existente() {
        // Arrange
        Suministro suministroExistente = new Suministro(1L, 1L, 5); // Artículo 1, cantidad 5

        when(suministroPersistencePort.obtenerSuministroPorArticuloId(1L)).thenReturn(Optional.of(suministroExistente));

        // Act
        suministroService.guardarSuministro(suministro1);

        // Assert
        verify(suministroPersistencePort).guardarSuministro(suministroExistente);
        verify(stockClient).actualizarStock(new SuministroRequest(1L, 10));
        assertEquals(15, suministroExistente.getCantidad(), "La cantidad de suministro debería haberse incrementado a 15.");
    }

    @Test
     void testGuardarSuministro_Nuevo() {
        // Arrange
        when(suministroPersistencePort.obtenerSuministroPorArticuloId(1L)).thenReturn(Optional.empty());

        // Act
        suministroService.guardarSuministro(suministro1);

        // Assert
        verify(suministroPersistencePort).guardarSuministro(suministro1);
        verify(stockClient).actualizarStock(new SuministroRequest(1L, 10));
    }

    @Test
     void testObtenerSuministroPorArticuloId() {
        // Arrange
        when(suministroPersistencePort.obtenerSuministroPorArticuloId(1L)).thenReturn(Optional.of(suministro1));

        // Act
        Optional<Suministro> result = suministroService.obtenerSuministroPorArticuloId(1L);

        // Assert
        verify(suministroPersistencePort).obtenerSuministroPorArticuloId(1L);
        assertTrue(result.isPresent(), "El suministro debería estar presente.");
        assertEquals(suministro1, result.get(), "El suministro obtenido debería ser el mismo que suministro1.");
    }

    @Test
     void testObtenerTodosSuministros() {
        // Arrange
        List<Suministro> suministros = new ArrayList<>();
        suministros.add(suministro1);
        suministros.add(suministro2);

        PagedResponse<Suministro> pagedResponse = new PagedResponse<>(suministros, 1, 2, 1, 1, true);
        when(suministroPersistencePort.obtenerTodosSuministros(anyInt(), anyInt(), anyString())).thenReturn(pagedResponse);

        // Act
        PagedResponse<Suministro> result = suministroService.obtenerTodosSuministros(0, 10, "fechaDeIngreso");

        // Assert
        verify(suministroPersistencePort).obtenerTodosSuministros(0, 10, "fechaDeIngreso");
        assertEquals(pagedResponse, result, "La respuesta paginada debería coincidir con la simulada.");
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenCantidadIsInvalid() {
        // Arrange
        suministro1.setCantidad(0); // Cantidad inválida

        // Act & Assert
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            suministroService.guardarSuministro(suministro1);
        });

        assertEquals(ConstantesDominio.CAMPO_CANTIDAD_POSITIVO, exception.getMessage());
    }

    @Test
    void shouldThrowEmptyFieldExceptionWhenArticuloIdIsNull() {
        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, () -> {
            new Suministro(1L, null, 10);
        });

        assertEquals(ConstantesDominio.CAMPO_ID_VACIO, exception.getMessage());
    }
}