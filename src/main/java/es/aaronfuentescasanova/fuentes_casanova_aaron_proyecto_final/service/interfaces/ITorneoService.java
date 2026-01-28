package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.TorneoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITorneoService {
    List<TorneoResponse> findAll();
    TorneoResponse findById(Long id);
    TorneoResponse create(TorneoRequest request);
    TorneoResponse update(Long id, TorneoRequest request);
    void delete(Long id);
    Page<TorneoResponse> findAll(Pageable pageable);

}
