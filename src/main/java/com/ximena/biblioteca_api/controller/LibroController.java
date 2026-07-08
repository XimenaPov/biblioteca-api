package com.ximena.biblioteca_api.controller;


import com.ximena.biblioteca_api.dto.LibroRequestDTO;
import com.ximena.biblioteca_api.dto.LibroResponseDTO;
import com.ximena.biblioteca_api.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // GET /api/libros → obtener todos
    @GetMapping
    public List<LibroResponseDTO> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    // GET /api/libros/1 → obtener por id
    @GetMapping("/{id}")
    public LibroResponseDTO obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    // POST /api/libros → crear
    @PostMapping
    public ResponseEntity<LibroResponseDTO> crear(@Valid @RequestBody LibroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(libroService.crear(dto));
    }

    // PUT /api/libros/1 → actualizar
    @PutMapping("/{id}")
    public LibroResponseDTO actualizar(@PathVariable Long id,
                                       @Valid @RequestBody LibroRequestDTO dto) {
        return libroService.actualizar(id, dto);
    }

    // DELETE /api/libros/1 → eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/libros/disponibles
    @GetMapping("/disponibles")
    public List<LibroResponseDTO> disponibles() {
        return libroService.obtenerDisponibles();
    }
}