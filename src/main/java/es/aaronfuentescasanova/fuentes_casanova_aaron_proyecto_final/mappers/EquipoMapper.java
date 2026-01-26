package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipoMapper {

    EquipoResponse toResponse(Equipo equipo);

}