package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.JugadorRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;

import java.util.List;

public interface IJugadorService {
    List<JugadorResponse> findAll();
    JugadorResponse findById(Long id);
    JugadorResponse create(JugadorRequest request);
    JugadorResponse update(Long id, JugadorRequest request);
    void delete(Long id);
}
