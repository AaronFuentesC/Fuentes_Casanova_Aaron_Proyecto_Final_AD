package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorneoRequest {
    @NotBlank(message = "El nombre del torneo es obligatorio")
    private String nombre;
    @NotBlank(message = "La temporada del torneo es obligatoria")
    private String temporada;

}
