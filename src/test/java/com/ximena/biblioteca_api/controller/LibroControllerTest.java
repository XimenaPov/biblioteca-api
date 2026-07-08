package com.ximena.biblioteca_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ximena.biblioteca_api.dto.LibroRequestDTO;
import com.ximena.biblioteca_api.dto.LibroResponseDTO;
import com.ximena.biblioteca_api.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibroController.class)
class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    private LibroRequestDTO requestDTO;
    private LibroResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new LibroRequestDTO();
        requestDTO.setTitulo("Clean Code");
        requestDTO.setAutor("Robert Martin");
        requestDTO.setIsbn("978-0132350884");

        responseDTO = new LibroResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitulo("Clean Code");
        responseDTO.setAutor("Robert Martin");
        responseDTO.setIsbn("978-0132350884");
        responseDTO.setDisponible(true);
    }

    @Test
    @DisplayName("GET /api/libros debe retornar lista de libros")
    void obtenerTodos_retornaLista() throws Exception {
        when(libroService.obtenerTodos()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Clean Code"))
                .andExpect(jsonPath("$[0].autor").value("Robert Martin"));
    }

    @Test
    @DisplayName("POST /api/libros debe crear libro y retornar 201")
    void crear_libroValido_retorna201() throws Exception {
        when(libroService.crear(any(LibroRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Clean Code"));
    }
}

