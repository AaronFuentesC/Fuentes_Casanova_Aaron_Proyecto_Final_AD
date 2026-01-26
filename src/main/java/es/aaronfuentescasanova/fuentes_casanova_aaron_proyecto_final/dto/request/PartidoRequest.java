package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoRequest {
    private LocalDate fecha;
    private int golesLocal;
    private int golesVisitante;
    private Long idEquipoLocal;
    private Long idEquipoVisitante;
    private Long idTorneo;
}
