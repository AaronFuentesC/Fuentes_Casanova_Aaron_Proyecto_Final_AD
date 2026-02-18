package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo,Long> {
    List<Equipo> findDistinctByPartidosLocal_TorneoOrPartidosVisitante_Torneo(Torneo torneoLocal, Torneo torneoVisitante);
}
