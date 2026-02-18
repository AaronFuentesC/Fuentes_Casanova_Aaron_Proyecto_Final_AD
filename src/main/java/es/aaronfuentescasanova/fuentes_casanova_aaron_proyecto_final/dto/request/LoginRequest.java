package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe tener un formato válido")
        String email,
        @NotBlank(message = "La contraseña no puede estar vacío")
        @Min(value = 4,message = "La contraseña debe tener como mínimo 4 caracteres")
        String password
) {

}
