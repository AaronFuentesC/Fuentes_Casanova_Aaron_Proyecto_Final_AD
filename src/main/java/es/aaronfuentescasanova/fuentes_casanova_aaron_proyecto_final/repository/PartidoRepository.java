package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Partido;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido,Long> {

    @Query("""
        SELECT COUNT(p) > 0
        FROM Partido p
        WHERE p.fecha = :fecha
        AND (p.equipoLocal.id = :equipoId OR p.equipoVisitante.id = :equipoId)
    """)
    boolean existsEquipoConPartidoEnFecha(
            @Param("equipoId") Long equipoId,
            @Param("fecha") LocalDate fecha
    );
    List<Partido> findByTorneo(Torneo torneo);
}
