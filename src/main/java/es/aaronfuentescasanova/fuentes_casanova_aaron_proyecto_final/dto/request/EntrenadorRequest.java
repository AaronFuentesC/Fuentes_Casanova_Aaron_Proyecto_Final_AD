package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorRequest {
    private Long id_equipo;
    @NotBlank(message = "El nombre del entrenador es obligatorio")
    private String nombre;
    @NotBlank(message = "La nacionalidad del entrenador es obligatoria")
    private String nacionalidad;
    @NotNull(message = "La fecha de nacimiento del entrenador es obligatoria")
    private LocalDate fechaNacimiento;
}
