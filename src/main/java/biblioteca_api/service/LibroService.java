package biblioteca_api.service;

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
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    // Obtener por ID
    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    // Crear libro
    public Libro crear(Libro libro) {
        return libroRepository.save(libro);
    }

    // Actualizar libro
    public Libro actualizar(Long id, Libro libroActualizado) {
        Libro libro = obtenerPorId(id);
        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setIsbn(libroActualizado.getIsbn());
        libro.setDisponible(libroActualizado.isDisponible());
        return libroRepository.save(libro);
    }

    // Eliminar libro
    public void eliminar(Long id) {
        obtenerPorId(id); // verifica que existe
        libroRepository.deleteById(id);
    }

    // Listar disponibles
    public List<Libro> obtenerDisponibles() {
        return libroRepository.findByDisponibleTrue();
    }
}
