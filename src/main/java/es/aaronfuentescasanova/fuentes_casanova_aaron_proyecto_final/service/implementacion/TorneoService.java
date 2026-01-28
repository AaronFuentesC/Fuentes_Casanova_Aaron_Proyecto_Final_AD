package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.TorneoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.TorneoMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.TorneoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.ITorneoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TorneoService implements ITorneoService {
    private final TorneoRepository torneoRepository;
    private final TorneoMapper torneoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TorneoResponse> findAll() {
        return torneoRepository.findAll().stream()
                .map(torneoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public TorneoResponse findById(Long id) {
        Torneo torneo = torneoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con id: " + id));
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
            throw new RuntimeException("Torneo no encontrado con id: " + id);
        }
        torneoRepository.deleteById(id);
    }

    @Override
    public Page<TorneoResponse> findAll(Pageable pageable) {
        return torneoRepository.findAll(pageable)
                .map(torneoMapper::toResponse);
    }
}
