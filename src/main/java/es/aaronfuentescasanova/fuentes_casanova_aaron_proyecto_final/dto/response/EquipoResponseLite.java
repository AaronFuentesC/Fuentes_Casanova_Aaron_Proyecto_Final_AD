package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipoResponseLite {
    private Long id;
    private String nombre;
}
