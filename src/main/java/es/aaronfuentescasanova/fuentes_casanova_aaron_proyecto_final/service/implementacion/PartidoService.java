package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.PartidoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.PartidoMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Partido;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.EquipoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.PartidoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.TorneoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.IPartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidoService implements IPartidoService {
    private final PartidoRepository partidoRepository;
    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;
    private final PartidoMapper partidoMapper;
    @Override
    public List<PartidoResponse> findAll() {
        return partidoRepository.findAll().stream()
                .map(partidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PartidoResponse findById(Long id) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        return partidoMapper.toResponse(partido);
    }

    @Override
    public PartidoResponse create(PartidoRequest request) {
        Torneo torneo = torneoRepository.findById(request.getIdTorneo())
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con id: " + request.getIdTorneo()));
        Equipo equipoLocal = equipoRepository.findById(request.getIdEquipoLocal())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getIdEquipoLocal()));
        Equipo equipoVisitante = equipoRepository.findById(request.getIdEquipoVisitante())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getIdEquipoVisitante()));
        Partido partido = Partido.builder()
                .torneo(torneo)
                .equipoLocal(equipoLocal)
                .equipoVisitante(equipoVisitante)
                .fecha(request.getFecha())
                .golesLocal(request.getGolesLocal())
                .golesVisitante(request.getGolesVisitante())
                .build();
        Partido savedPartido = partidoRepository.save(partido);
        return partidoMapper.toResponse(savedPartido);

    }

    @Override
    public PartidoResponse update(Long id, PartidoRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!partidoRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }
        partidoRepository.deleteById(id);
    }
}
