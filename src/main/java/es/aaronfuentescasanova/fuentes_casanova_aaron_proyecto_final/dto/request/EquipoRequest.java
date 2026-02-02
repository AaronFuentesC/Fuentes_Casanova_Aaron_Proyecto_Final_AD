package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoRequest {
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombre;
    @NotBlank(message = "La ciudad del equipo es obligatoria")
    private String ciudad;
    @NotBlank(message = "El estadio del equipo es obligatorio")
    private String estadio;
    @NotBlank(message = "El país del equipo es obligatorio")
    private String pais;
    @NotNull(message = "El equipo debe tener una fecha de fundación")
    @Past(message="La fecha de fundación del equipo debe ser una fecha pasada")
    private LocalDate fechaFundacion;
}
