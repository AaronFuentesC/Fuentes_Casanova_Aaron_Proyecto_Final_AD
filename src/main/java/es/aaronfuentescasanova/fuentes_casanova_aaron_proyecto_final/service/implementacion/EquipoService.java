package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.EquipoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.EquipoMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Jugador;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.EquipoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.TorneoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.IEquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class EquipoService implements IEquipoService {
    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;


    @Transactional(readOnly = true)
    @Override
    public List<EquipoResponse> findAll() {
        return equipoRepository.findAll().stream()
                .map(equipoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public EquipoResponse findById(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
        return equipoMapper.toResponse(equipo);
    }

    @Override
    public EquipoResponse create(EquipoRequest request) {
        Equipo equipo = Equipo.builder()
                .nombre(request.getNombre())
                .ciudad(request.getCiudad())
                .pais(request.getPais())
                .estadio(request.getEstadio())
                .fechaFundacion(request.getFechaFundacion())
                .build();
        Equipo savedEquipo = equipoRepository.save(equipo);
        return equipoMapper.toResponse(savedEquipo);

    }

    @Override
    public EquipoResponse update(Long id, EquipoRequest request) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
        equipo.setNombre(request.getNombre());
        equipo.setCiudad(request.getCiudad());
        equipo.setPais(request.getPais());
        equipo.setEstadio(request.getEstadio());
        Equipo updatedEquipo = equipoRepository.save(equipo);
        return equipoMapper.toResponse(updatedEquipo);
    }

    @Override
    public void delete(Long id) {

        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));

        for (Jugador j : equipo.getJugadores()) {
            j.setEquipo(null);
        }

        if (equipo.getEntrenador() != null) {
            equipo.getEntrenador().setEquipo(null);
        }

        for (Torneo torneo : equipo.getTorneos()) {
            torneo.getEquipos().remove(equipo);
        }

        equipo.getTorneos().clear();

        equipoRepository.save(equipo);

        equipoRepository.delete(equipo);
    }






}
