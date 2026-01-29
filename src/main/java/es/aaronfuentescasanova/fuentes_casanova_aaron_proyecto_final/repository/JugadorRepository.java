package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Jugador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador,Long> {
    long countByEquipoId(Long equipoId);

    List<Jugador> findByEquipoId(Long equipoId);

    boolean existsByEquipoIdAndDorsal(Long equipoId, Integer dorsal);

    boolean existsByEquipoIdAndPosicion(Long equipoId, String posicion);

    Page<Jugador> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

}
