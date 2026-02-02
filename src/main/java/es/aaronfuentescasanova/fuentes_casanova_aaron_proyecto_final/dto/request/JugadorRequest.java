package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorRequest {

    @NotBlank(message = "El nombre del jugador es obligatorio")
    private String nombre;
    @NotNull(message = "La fecha de nacimiento del jugdaor es obligatoria")
    @Past(message = "La fecha de nacimiento del jugador debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    private String posicion;
    @NotNull(message = "El dorsal del jugador es obligatorio")
    private int dorsal;

    @NotNull(message = "El id del equipo es obligatorio")
    private Long id_equipo;
}
