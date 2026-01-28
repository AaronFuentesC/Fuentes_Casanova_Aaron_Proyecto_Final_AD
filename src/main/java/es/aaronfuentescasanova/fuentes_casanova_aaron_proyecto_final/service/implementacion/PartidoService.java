package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.PartidoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.PartidoMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Partido;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.EquipoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.JugadorRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.PartidoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.TorneoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.IPartidoService;
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
public class PartidoService implements IPartidoService {
    private final PartidoRepository partidoRepository;
    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoMapper partidoMapper;


    @Transactional(readOnly = true)
    @Override
    public List<PartidoResponse> findAll() {
        return partidoRepository.findAll().stream()
                .map(partidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public PartidoResponse findById(Long id) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));
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

        if (!torneo.getEquipos().contains(equipoLocal) ||
                !torneo.getEquipos().contains(equipoVisitante)) {
            throw new RuntimeException("Los equipos deben estar inscritos en el torneo");
        }
        if (request.getGolesLocal() < 0 || request.getGolesVisitante() < 0) {
            throw new RuntimeException("Los goles no pueden ser negativos");
        }
        if (!jugadorRepository.existsByEquipoIdAndPosicion(request.getIdEquipoLocal(), "portero")) {
            throw new RuntimeException("El equipo debe tener al menos un portero");
        }
        if (!jugadorRepository.existsByEquipoIdAndPosicion(request.getIdEquipoVisitante(), "portero")) {
            throw new RuntimeException("El equipo debe tener al menos un portero");
        }
        if (partidoRepository.existsEquipoConPartidoEnFecha(request.getIdEquipoLocal(), request.getFecha())) {
            throw new RuntimeException("El equipo ya tiene un partido ese día");
        }
        if (partidoRepository.existsEquipoConPartidoEnFecha(request.getIdEquipoVisitante(), request.getFecha())) {
            throw new RuntimeException("El equipo ya tiene un partido ese día");
        }
        if (equipoLocal.getId().equals(equipoVisitante.getId())) {
            throw new RuntimeException("Un equipo no puede jugar contra sí mismo");
        }


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
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));
        Torneo torneo = torneoRepository.findById(request.getIdTorneo())
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con id: " + request.getIdTorneo()));
        Equipo equipoLocal = equipoRepository.findById(request.getIdEquipoLocal())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getIdEquipoLocal()));
        Equipo equipoVisitante = equipoRepository.findById(request.getIdEquipoVisitante())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getIdEquipoVisitante()));
        partido.setFecha(request.getFecha());
        partido.setGolesLocal(request.getGolesLocal());
        partido.setGolesVisitante(request.getGolesVisitante());
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setTorneo(torneo);
        partidoRepository.save(partido);
        return partidoMapper.toResponse(partido);


    }

    @Override
    public void delete(Long id) {
        if (!partidoRepository.existsById(id)) {
            throw new RuntimeException("Partido no encontrado con id: " + id);
        }
        partidoRepository.deleteById(id);
    }

    @Override
    public Page<PartidoResponse> findAll(Pageable pageable) {
        return partidoRepository.findAll(pageable)
                .map(partidoMapper::toResponse);
    }

}
