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
@Table(name = "partido")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate fecha;
    private int golesLocal;
    private int golesVisitante;

    @ManyToOne
    @JoinColumn(name = "torneo_id")
    @JsonIgnore
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "equipo_local_id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante_id")
    private Equipo equipoVisitante;


}
