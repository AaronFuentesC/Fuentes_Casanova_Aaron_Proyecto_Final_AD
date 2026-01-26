package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EntrenadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Entrenador;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntrenadorMapper {


    EntrenadorResponse toResponse(Entrenador entrenador);

}