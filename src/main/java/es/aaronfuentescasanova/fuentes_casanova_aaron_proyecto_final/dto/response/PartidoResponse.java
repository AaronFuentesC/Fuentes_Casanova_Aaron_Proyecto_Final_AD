package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoResponse {
    private Long id;
    private LocalDate fecha;
    private int golesLocal;
    private int golesVisitante;
    private TorneoResponseLite torneo;
    private EquipoResponseLite equipoLocal;
    private EquipoResponseLite equipoVisitante;
}
