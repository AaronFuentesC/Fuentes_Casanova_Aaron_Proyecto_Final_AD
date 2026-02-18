package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "jugador")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private LocalDate fechaNacimiento;
    private String posicion;
    private int dorsal;


    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
}
