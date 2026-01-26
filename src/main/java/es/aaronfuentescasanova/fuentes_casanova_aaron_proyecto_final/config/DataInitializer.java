package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.config;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.*;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final TorneoRepository torneoRepository;
    private final PartidoRepository partidoRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {

            // ======================
            // EQUIPOS
            // ======================
            Equipo barcelona = Equipo.builder()
                    .nombre("FC Barcelona")
                    .ciudad("Barcelona")
                    .pais("España")
                    .estadio("Camp Nou")
                    .fechaFundacion(LocalDate.of(1899, 11, 29))
                    .build();

            Equipo madrid = Equipo.builder()
                    .nombre("Real Madrid")
                    .ciudad("Madrid")
                    .pais("España")
                    .estadio("Santiago Bernabéu")
                    .fechaFundacion(LocalDate.of(1902, 3, 6))
                    .build();

            equipoRepository.saveAll(List.of(barcelona, madrid));

            // ======================
            // ENTRENADORES (1:1)
            // ======================
            Entrenador xavi = Entrenador.builder()
                    .nombre("Xavi Hernández")
                    .fechaNacimiento(LocalDate.of(1980, 1, 25))
                    .nacionalidad("España")
                    .equipo(barcelona)
                    .build();

            Entrenador ancelotti = Entrenador.builder()
                    .nombre("Carlo Ancelotti")
                    .fechaNacimiento(LocalDate.of(1959, 6, 10))
                    .nacionalidad("Italia")
                    .equipo(madrid)
                    .build();

            entrenadorRepository.saveAll(List.of(xavi, ancelotti));

            // ======================
            // JUGADORES (N:1)
            // ======================
            Jugador pedri = Jugador.builder()
                    .nombre("Pedri")
                    .fechaNacimiento(LocalDate.of(2002, 11, 25))
                    .posicion("Centrocampista")
                    .dorsal(8)
                    .equipo(barcelona)
                    .build();

            Jugador gavi = Jugador.builder()
                    .nombre("Gavi")
                    .fechaNacimiento(LocalDate.of(2004, 8, 5))
                    .posicion("Centrocampista")
                    .dorsal(6)
                    .equipo(barcelona)
                    .build();

            Jugador bellingham = Jugador.builder()
                    .nombre("Jude Bellingham")
                    .fechaNacimiento(LocalDate.of(2003, 6, 29))
                    .posicion("Centrocampista")
                    .dorsal(5)
                    .equipo(madrid)
                    .build();

            jugadorRepository.saveAll(List.of(pedri, gavi, bellingham));

            // ======================
            // TORNEO (N:M)
            // ======================
            Torneo liga = Torneo.builder()
                    .nombre("LaLiga")
                    .temporada("2024/2025")
                    .equipos(List.of(barcelona, madrid))
                    .build();

            torneoRepository.save(liga);

            // ======================
            // PARTIDO (Transaccional)
            // ======================
            Partido clasico = Partido.builder()
                    .fecha(LocalDate.of(2025, 3, 10))
                    .golesLocal(2)
                    .golesVisitante(1)
                    .equipoLocal(barcelona)
                    .equipoVisitante(madrid)
                    .torneo(liga)
                    .build();

            partidoRepository.save(clasico);
        };
    }
}
