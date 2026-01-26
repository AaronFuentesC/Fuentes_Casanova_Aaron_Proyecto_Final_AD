package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorRequest {
    private String nombre;
    private LocalDate fechaNacimiento;
    private String posicion;
    private int dorsal;
    private Long id_equipo;
}
