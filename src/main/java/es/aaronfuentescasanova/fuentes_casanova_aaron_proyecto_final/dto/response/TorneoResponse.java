package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorneoResponse {
    private Long id;
    private String nombre;
    private String temporada;
    private List<EquipoResponse> equipos;
}
