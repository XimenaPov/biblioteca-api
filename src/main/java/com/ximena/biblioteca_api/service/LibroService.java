package com.ximena.biblioteca_api.service;

import com.ximena.biblioteca_api.dto.LibroRequestDTO;
import com.ximena.biblioteca_api.dto.LibroResponseDTO;
import com.ximena.biblioteca_api.exception.RecursoNoEncontradoException;
import com.ximena.biblioteca_api.model.Libro;
import com.ximena.biblioteca_api.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    public List<LibroResponseDTO> obtenerTodos() {
        return libroRepository.findAll()
                .stream()
                .map(libro -> {
                    LibroResponseDTO response = new LibroResponseDTO();
                    response.setId(libro.getId());
                    response.setTitulo(libro.getTitulo());
                    response.setAutor(libro.getAutor());
                    response.setIsbn(libro.getIsbn());
                    response.setDisponible(libro.isDisponible());
                    return response;
                })
                .toList();
    }

    // Obtener por ID
    public LibroResponseDTO obtenerPorId(Long id) {
        Libro libro = buscarLibroOLanzarExcepcion(id);
        LibroResponseDTO response = new LibroResponseDTO();
        response.setId(libro.getId());
        response.setTitulo(libro.getTitulo());
        response.setAutor(libro.getAutor());
        response.setIsbn(libro.getIsbn());
        response.setDisponible(libro.isDisponible());
        return response;
    }

    // Crear libro
    public LibroResponseDTO crear(LibroRequestDTO dto) {
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setIsbn(dto.getIsbn());
        libro.setDisponible(true);

        Libro guardado = libroRepository.save(libro);

        LibroResponseDTO response = new LibroResponseDTO();
        response.setId(guardado.getId());
        response.setTitulo(guardado.getTitulo());
        response.setAutor(guardado.getAutor());
        response.setIsbn(guardado.getIsbn());
        response.setDisponible(guardado.isDisponible());
        return response;
    }

    // Método privado interno — solo para uso dentro del Service
    private Libro buscarLibroOLanzarExcepcion(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Libro no encontrado con id: " + id));
    }

    // Actualizar libro
    public LibroResponseDTO actualizar(Long id, LibroRequestDTO dto) {
        Libro libro = buscarLibroOLanzarExcepcion(id);
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setIsbn(dto.getIsbn());
        Libro guardado = libroRepository.save(libro);
        LibroResponseDTO response = new LibroResponseDTO();
        response.setId(guardado.getId());
        response.setTitulo(guardado.getTitulo());
        response.setAutor(guardado.getAutor());
        response.setIsbn(guardado.getIsbn());
        response.setDisponible(guardado.isDisponible());
        return response;
    }

    // Eliminar libro
    public void eliminar(Long id) {
        buscarLibroOLanzarExcepcion(id);
        libroRepository.deleteById(id);
    }

    // Listar disponibles
    public List<LibroResponseDTO> obtenerDisponibles() {
        return libroRepository.findByDisponibleTrue()
                .stream()
                .map(libro -> {
                    LibroResponseDTO response = new LibroResponseDTO();
                    response.setId(libro.getId());
                    response.setTitulo(libro.getTitulo());
                    response.setAutor(libro.getAutor());
                    response.setIsbn(libro.getIsbn());
                    response.setDisponible(libro.isDisponible());
                    return response;
                })
                .toList();
    }
}