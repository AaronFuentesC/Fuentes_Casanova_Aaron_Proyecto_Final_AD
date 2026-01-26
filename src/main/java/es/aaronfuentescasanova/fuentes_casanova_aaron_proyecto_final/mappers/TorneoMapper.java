package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TorneoMapper {

    TorneoResponse toResponse(Torneo torneo);

}