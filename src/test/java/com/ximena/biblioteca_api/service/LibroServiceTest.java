package com.ximena.biblioteca_api.service;

import com.ximena.biblioteca_api.dto.LibroRequestDTO;
import com.ximena.biblioteca_api.dto.LibroResponseDTO;
import com.ximena.biblioteca_api.exception.RecursoNoEncontradoException;
import com.ximena.biblioteca_api.model.Libro;
import com.ximena.biblioteca_api.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    private Libro libro;
    private LibroRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("Clean Code");
        libro.setAutor("Robert Martin");
        libro.setIsbn("978-0132350884");
        libro.setDisponible(true);

        requestDTO = new LibroRequestDTO();
        requestDTO.setTitulo("Clean Code");
        requestDTO.setAutor("Robert Martin");
        requestDTO.setIsbn("978-0132350884");
    }

    @Test
    @DisplayName("Debe retornar libro cuando existe el id")
    void obtenerPorId_cuandoExiste_retornaLibro() {
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        LibroResponseDTO resultado = libroService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getTitulo());
        assertEquals("Robert Martin", resultado.getAutor());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el id no existe")
    void obtenerPorId_cuandoNoExiste_lanzaExcepcion() {
        when(libroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class,
                () -> libroService.obtenerPorId(99L));
    }

    @Test
    @DisplayName("Debe guardar libro y retornarlo")
    void crear_libroValido_retornaLibroGuardado() {
        when(libroRepository.save(any(Libro.class))).thenReturn(libro);

        LibroResponseDTO resultado = libroService.crear(requestDTO);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getTitulo());
        verify(libroRepository, times(1)).save(any(Libro.class));
    }

    @Test
    @DisplayName("Debe eliminar libro cuando existe")
    void eliminar_cuandoExiste_eliminaCorrectamente() {
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));
        doNothing().when(libroRepository).deleteById(1L);

        libroService.eliminar(1L);

        verify(libroRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("No debe eliminar si el libro no existe")
    void eliminar_cuandoNoExiste_lanzaExcepcion() {
        when(libroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class,
                () -> libroService.eliminar(99L));

        verify(libroRepository, never()).deleteById(anyLong());
    }
}