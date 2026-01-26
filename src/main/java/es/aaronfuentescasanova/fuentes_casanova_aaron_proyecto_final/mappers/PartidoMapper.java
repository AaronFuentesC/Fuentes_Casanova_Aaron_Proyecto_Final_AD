package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Partido;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartidoMapper {

    PartidoResponse toResponse(Partido partido);




}