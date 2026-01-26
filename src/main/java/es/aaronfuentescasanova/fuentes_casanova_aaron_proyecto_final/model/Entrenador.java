package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "entrenador")
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private LocalDate fechaNacimiento;
    private String nacionalidad;

    @OneToOne
    @JoinColumn(name = "equipo_id", unique = true)
    private Equipo equipo;
}
