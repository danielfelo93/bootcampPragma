package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.domain.exception.EmptyFieldException;
import com.bootcamp.emazonapi.domain.exception.LimitExceededException;
import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazonapi.driven.entity.CategoriaEntity;
import com.bootcamp.emazonapi.driven.entity.MarcaEntity;
import com.bootcamp.emazonapi.driven.repository.ICategoriaRepository;
import com.bootcamp.emazonapi.driven.repository.IMarcaRepository;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticuloUseCaseTest {

    @Mock
    private IArticuloPersistencePort articuloPersistencePort;

    @Mock
    private IMarcaPersistencePort marcaPersistencePort;

    @Mock
    private ICategoriaPersistencePort categoriaPersistencePort;

    @InjectMocks
    private ArticuloUseCase articuloUseCase;

    private Articulo articulo1;
    private Articulo articulo2;
    private Articulo articulo3;
    private Marca marca1;
    private Set<Categoria> categorias;

    private void assertPagedResponseContentEquals(PagedResponse<Articulo> expected, PagedResponse<Articulo> actual) {
        assertEquals(expected.getContent(), actual.getContent(), "Contenido no coincide");
    }

    /*private Categoria categoria1;
    private Categoria categoria2;
    private Categoria categoria3;
    private Marca marca1;
    private Marca marca2;
    private Marca marca3;*/

    @BeforeEach
    void setUp() {
        marca1 = new Marca(1L, "MarcaEjemplo", "..................");
        categorias = new HashSet<>();
        categorias.add(new Categoria(1L, "CategoríaEjemplo", "Descripción de Categoría"));
        articulo1 = new Articulo(1L, "Zapatos", "Descripción de Zapatos", 1,new BigDecimal(1000), marca1, categorias);
        articulo2 = new Articulo(2L, "Camiseta", "Descripción de Camiseta", 2, new BigDecimal(2500), marca1, categorias);
        articulo2 = new Articulo(3L, "Collar", "Descripción de Collar",12, new BigDecimal(50000), marca1, categorias);
        /*categoria1 = new Categoria(1L, "Ropa hombres", "Descripcion de ropa masculina" );
        categoria2 = new Categoria(2L, "Zapatos hombres", "Descripcion de Zapatos masculinos" );
        categoria3 = new Categoria(3L, "Accesorios hombres", "Descripcion de Accesorios masculinos" );
        marca1 = new Marca(1L, "Zara", "Descripción de Zara");
        marca2 = new Marca(2L, "Arturo Calle", "Descripción de Arturo Calle");
        marca3 = new Marca(3L, "Ragged", "Descripción de Ragged");*/
    }

    @Test
    void shouldReturnArticulosSuccessfullyById() {
        // Arrange
        List<Articulo> articulos = Arrays.asList(articulo1, articulo2, articulo3);
        PagedResponse<Articulo> pagedResponse = new PagedResponse<>(articulos, 0, 3, 3, 1, true);
        when(articuloPersistencePort.listarArticulos(0, 3, "")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Articulo> result = articuloUseCase.listarArticulos(0, 3, "");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(articuloPersistencePort).listarArticulos(0, 3, "");
    }

    @Test
    void shouldReturnArticulosSuccessfullyByNombreAsc() {
        // Arrange
        List<Articulo> articulos = Arrays.asList(articulo1, articulo2, articulo3);
        PagedResponse<Articulo> pagedResponse = new PagedResponse<>(articulos, 0, 3, 3, 1, true);
        when(articuloPersistencePort.listarArticulos(0, 3, "asc")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Articulo> result = articuloUseCase.listarArticulos(0, 3, "asc");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(articuloPersistencePort).listarArticulos(0, 3, "asc");
    }

    @Test
    void shouldReturnArticulosSuccessfullyByNombreDesc() {
        // Arrange
        List<Articulo> articulos = Arrays.asList(articulo1, articulo2, articulo3);
        PagedResponse<Articulo> pagedResponse = new PagedResponse<>(articulos, 0, 3, 3, 1, true);
        when(articuloPersistencePort.listarArticulos(0, 3, "desc")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Articulo> result = articuloUseCase.listarArticulos(0, 3, "desc");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(articuloPersistencePort).listarArticulos(0, 3, "desc");
    }



    @Test
    void shouldReturnEmptyListWhenNoArticulosFound() {
        // Arrange
        PagedResponse<Articulo> pagedResponse = new PagedResponse<>(Arrays.asList(), 0, 3, 0, 0, true);
        when(articuloPersistencePort.listarArticulos(0, 3, "")).thenReturn(pagedResponse);

        // Act
        PagedResponse<Articulo> result = articuloUseCase.listarArticulos(0, 3, "");

        // Assert
        assertPagedResponseContentEquals(pagedResponse, result);
        verify(articuloPersistencePort).listarArticulos(0, 3, "");
    }

    @Test
    void shouldAddArticuloSuccessfully() {

        //Arrange
        Long marcaId = 1L;
        Set<Long> categoriaIdsSet = new HashSet<>(Arrays.asList(1L, 2L));
        List<Long> categoriaIdsList = new ArrayList<>(categoriaIdsSet);

        when(categoriaPersistencePort.obtenerCategoriaPorId(1L)).thenReturn(Optional.of(new Categoria(1L, "Categoria1", "Descripción Categoria1")));
        when(categoriaPersistencePort.obtenerCategoriaPorId(2L)).thenReturn(Optional.of(new Categoria(2L, "Categoria2", "Descripción Categoria2")));
        when(marcaPersistencePort.obtenerMarcaPorId(marcaId)).thenReturn(Optional.of(new Marca(marcaId, "MarcaEjemplo", "Descripción de Marca")));

        // Act
        articuloUseCase.guardarArticulo(articulo1, marcaId, (List<Long>) categoriaIdsList);

        // Assert
        verify(articuloPersistencePort, times(1)).guardarArticulo(articulo1);
    }

    @Test
    void shouldThrowEmptyFieldExceptionWhenNombreIsEmpty() {
        // Arrange
        assertThrows(EmptyFieldException.class, () -> {
            new Articulo(4L, "", "Descripción válida",1,new BigDecimal("1000"), marca1, categorias);
        });
    }
    @Test
    void shouldThrowEmptyFieldExceptionWhenDescripcionIsEmpty() {
        // Arrange
        assertThrows(EmptyFieldException.class, () -> {
            new Articulo(5L, "EjemploArticulo", "",1,new BigDecimal("1000"), marca1, categorias);
        });
    }
    @Test
    void shouldThrowCharacterLimitExceededExceptionWhenNombreExceedsLength() {
        // Arrange
        assertThrows(LimitExceededException.class, () -> {
            new Articulo(6L, "Nombre que excede los 50 caracteres permitidos por la validación", "Ejemplo de una descripcion válida",1,new BigDecimal("1000"), marca1, categorias);
        });
    }
    @Test
    void shouldThrowCharacterLimitExceededExceptionWhenDescripcionExceedsLength() {
        // Arrange
        assertThrows(LimitExceededException.class, () -> {
            new Articulo(7L, "EjemploArticulo", "Ejemplo de una descripcion Descripción que excede los 90 caracteres permitidos por la validación y debería devolver un error",1,new BigDecimal("1000"), marca1, categorias);
        });
    }
}
