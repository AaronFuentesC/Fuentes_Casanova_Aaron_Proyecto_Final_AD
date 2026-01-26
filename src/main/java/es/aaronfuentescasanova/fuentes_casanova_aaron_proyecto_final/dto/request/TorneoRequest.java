package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorneoRequest {
    private String nombre;
    private String temporada;

}
