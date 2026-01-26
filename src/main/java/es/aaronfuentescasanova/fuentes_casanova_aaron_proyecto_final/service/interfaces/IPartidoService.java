package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.PartidoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;

import java.util.List;

public interface IPartidoService {
    List<PartidoResponse> findAll();
    PartidoResponse findById(Long id);
    PartidoResponse create(PartidoRequest request);
    PartidoResponse update(Long id, PartidoRequest request);
    void delete(Long id);
}
