package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoRequest {

    @NotNull(message = "La fecha del partido es obligatoria")
    @PastOrPresent(message = "La fecha del partido no puede ser futura")
    private LocalDate fecha;
    @Min(value = 0, message = "Los goles del equipo local no pueden ser negativos")
    private int golesLocal;
    @Min(value = 0, message = "Los goles del equipo visitante no pueden ser negativos")
    private int golesVisitante;
    @NotNull(message = "El id del equipo local es obligatorio")
    private Long idEquipoLocal;
    @NotNull(message = "El id del equipo visitante es obligatorio")
    private Long idEquipoVisitante;
    @NotNull(message = "El id del torneo es obligatorio")
    private Long idTorneo;
}
