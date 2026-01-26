package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoRequest {
    private String nombre;
    private String ciudad;
    private String estadio;
    private String pais;
    private LocalDate fechaFundacion;
}
