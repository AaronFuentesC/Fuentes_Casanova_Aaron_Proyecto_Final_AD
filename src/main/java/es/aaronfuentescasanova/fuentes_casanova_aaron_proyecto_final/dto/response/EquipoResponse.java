package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Entrenador;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Jugador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoResponse {

    private Long id;
    private String nombre;
    private String ciudad;
    private String estadio;
    private String pais;
    private LocalDate fechaFundacion;
    private EntrenadorResponse entrenador;
    private List<JugadorResponse> jugadores;
}
