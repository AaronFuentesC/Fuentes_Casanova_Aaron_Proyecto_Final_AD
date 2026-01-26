package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.EntrenadorRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EntrenadorResponse;

import java.util.List;

public interface IEntrenadorService {
    List<EntrenadorResponse> findAll();
    EntrenadorResponse findById(Long id);
    EntrenadorResponse create(EntrenadorRequest request);
    EntrenadorResponse update(Long id, EntrenadorRequest request);
    void delete(Long id);
}
