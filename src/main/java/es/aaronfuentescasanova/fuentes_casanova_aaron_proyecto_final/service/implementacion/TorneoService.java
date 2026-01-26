package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.TorneoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.TorneoMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.TorneoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.ITorneoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TorneoService implements ITorneoService {
    private final TorneoRepository torneoRepository;
    private final TorneoMapper torneoMapper;
    @Override
    public List<TorneoResponse> findAll() {
        return torneoRepository.findAll().stream()
                .map(torneoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TorneoResponse findById(Long id) {
        Torneo torneo = torneoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        return torneoMapper.toResponse(torneo);
    }

    @Override
    public TorneoResponse create(TorneoRequest request) {
        Torneo torneo = Torneo.builder()
                .nombre(request.getNombre())
                .temporada(request.getTemporada())
                .build();
        Torneo savedTorneo = torneoRepository.save(torneo);
        return torneoMapper.toResponse(savedTorneo);

    }

    @Override
    public TorneoResponse update(Long id, TorneoRequest request) {
        Torneo torneo = torneoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con id: " + id));
        torneo.setNombre(request.getNombre());
        torneo.setTemporada(request.getTemporada());
        Torneo updatedTorneo = torneoRepository.save(torneo);
        return torneoMapper.toResponse(updatedTorneo);
    }

    @Override
    public void delete(Long id) {
        if (!torneoRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }
        torneoRepository.deleteById(id);
    }
}
