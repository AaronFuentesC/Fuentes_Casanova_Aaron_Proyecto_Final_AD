package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Jugador;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JugadorMapper {

    JugadorResponse toResponse(Jugador jugador);

}