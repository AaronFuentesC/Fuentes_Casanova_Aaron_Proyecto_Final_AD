package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EntrenadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Entrenador;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Jugador;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TorneoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "temporada", target = "temporada") // aseguramos que se mapea
    @Mapping(source = "equipos", target = "equipos")
    TorneoResponse toResponse(Torneo torneo);

    // Mapear lista de equipos
    default List<EquipoResponse> mapEquipos(List<Equipo> equipos) {
        if (equipos == null) return null;
        return equipos.stream().map(this::mapEquipo).collect(Collectors.toList());
    }

    // Mapear un solo equipo
    default EquipoResponse mapEquipo(Equipo equipo) {
        if (equipo == null) return null;
        return new EquipoResponse(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getCiudad(),
                equipo.getEstadio(),
                equipo.getPais(),
                equipo.getFechaFundacion(),
                mapEntrenador(equipo.getEntrenador()),
                mapJugadores(equipo.getJugadores())
        );
    }

    // Mapear entrenador
    default EntrenadorResponse mapEntrenador(Entrenador entrenador) {
        if (entrenador == null) return null;
        return new EntrenadorResponse(
                entrenador.getId(),
                entrenador.getNombre(),
                entrenador.getNacionalidad()
        );
    }

    // Mapear lista de jugadores
    default List<JugadorResponse> mapJugadores(List<Jugador> jugadores) {
        if (jugadores == null) return null;
        return jugadores.stream().map(j -> new JugadorResponse(
                j.getId(),
                j.getNombre(),
                j.getFechaNacimiento().toString(),
                j.getPosicion(),
                j.getDorsal()
        )).collect(Collectors.toList());
    }
}
