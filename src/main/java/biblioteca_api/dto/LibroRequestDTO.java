package biblioteca_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LibroRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    private String isbn;
}
