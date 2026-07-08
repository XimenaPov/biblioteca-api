package biblioteca_api.dto;

import lombok.Data;

@Data
public class LibroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;
}
