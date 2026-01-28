package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponseLite;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponseLite;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Partido;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartidoMapper {

    @Mapping(source = "torneo", target = "torneo", qualifiedByName = "mapTorneoSimple")
    @Mapping(source = "equipoLocal", target = "equipoLocal", qualifiedByName = "mapEquipoSimple")
    @Mapping(source = "equipoVisitante", target = "equipoVisitante", qualifiedByName = "mapEquipoSimple")
    PartidoResponse toResponse(Partido partido);

    @Named("mapTorneoSimple")
    default TorneoResponseLite mapTorneoSimple(Torneo torneo) {
        if (torneo == null) return null;
        return TorneoResponseLite.builder()
                .id(torneo.getId())
                .nombre(torneo.getNombre())
                .temporada(torneo.getTemporada())
                .build();
    }

    @Named("mapEquipoSimple")
    default EquipoResponseLite mapEquipoSimple(Equipo equipo) {
        if (equipo == null) return null;
        return EquipoResponseLite.builder()
                .id(equipo.getId())
                .nombre(equipo.getNombre())
                .build();
    }
}
