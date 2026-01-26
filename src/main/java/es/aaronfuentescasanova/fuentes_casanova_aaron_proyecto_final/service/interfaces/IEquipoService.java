package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.EquipoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponse;

import java.util.List;

public interface IEquipoService {
    List<EquipoResponse> findAll();
    EquipoResponse findById(Long id);
    EquipoResponse create(EquipoRequest request);
    EquipoResponse update(Long id, EquipoRequest request);
    void delete(Long id);

}
