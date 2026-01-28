package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TorneoResponseLite {
    Long id;
    String nombre;
    String temporada;

}
