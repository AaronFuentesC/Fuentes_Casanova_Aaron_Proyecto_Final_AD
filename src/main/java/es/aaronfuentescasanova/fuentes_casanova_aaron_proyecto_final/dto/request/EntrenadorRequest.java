package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorRequest {
    private Long id_equipo;
    private String nombre;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
}
