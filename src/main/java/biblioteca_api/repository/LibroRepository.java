package biblioteca_api.repository;

import com.ximena.biblioteca_api.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Spring genera el SQL automáticamente por el nombre del método
    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findByDisponibleTrue();

    List<Libro> findByAutor(String autor);
}
