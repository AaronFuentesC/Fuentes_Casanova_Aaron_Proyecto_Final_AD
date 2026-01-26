package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private String ciudad;
    private String estadio;
    private String pais;
    private LocalDate fechaFundacion;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Jugador> jugadores = new ArrayList<>();

    @OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL)
    private Entrenador entrenador;
    // Relación con partido como local
    @OneToMany(mappedBy = "equipoLocal")
    private List<Partido> partidosLocal;

    // Relación con partido como visitante
    @OneToMany(mappedBy = "equipoVisitante")
    private List<Partido> partidosVisitante;

    // Relación con torneo
    @ManyToMany(mappedBy = "equipos")
    private List<Torneo> torneos;
}
