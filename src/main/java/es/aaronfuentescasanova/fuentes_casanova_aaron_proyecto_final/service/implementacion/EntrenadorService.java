package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.EntrenadorRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EntrenadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.EntrenadorMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Entrenador;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.EntrenadorRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.EquipoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.IEntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EntrenadorService implements IEntrenadorService {
    private final EntrenadorRepository entrenadorRepository;
    private final EntrenadorMapper entrenadorMapper;
    private final EquipoRepository equipoRepository;


    @Transactional(readOnly=true)
    @Override
    public List<EntrenadorResponse> findAll() {
        return entrenadorRepository.findAll().stream()
                .map(entrenadorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly=true)
    @Override
    public EntrenadorResponse findById(Long id) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
        return entrenadorMapper.toResponse(entrenador);
    }

    @Override
    public EntrenadorResponse create(EntrenadorRequest request) {
        Equipo equipo = equipoRepository.findById(request.getId_equipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getId_equipo()));
        if (equipo.getEntrenador() != null) {
            throw new RuntimeException("El equipo ya tiene un entrenador asignado");
        }

        Entrenador entrenador = Entrenador.builder()
                .equipo(equipo)
                .fechaNacimiento(request.getFechaNacimiento())
                .nombre(request.getNombre())
                .nacionalidad(request.getNacionalidad())
                .build();
        Entrenador savedEntrenador = entrenadorRepository.save(entrenador);
        return entrenadorMapper.toResponse(savedEntrenador);
    }

    @Override
    public EntrenadorResponse update(Long id, EntrenadorRequest request) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
        Equipo equipo = equipoRepository.findById(request.getId_equipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getId_equipo()));
        entrenador.setEquipo(equipo);
        entrenador.setFechaNacimiento(request.getFechaNacimiento());
        entrenador.setNombre(request.getNombre());
        entrenador.setNacionalidad(request.getNacionalidad());
        Entrenador updatedEntrenador = entrenadorRepository.save(entrenador);
        return entrenadorMapper.toResponse(updatedEntrenador);
    }

    @Override
    public void delete(Long id) {
        if (!entrenadorRepository.existsById(id)) {
            throw new RuntimeException("Entrenador no encontrado con id: " + id);
        }
        entrenadorRepository.deleteById(id);

    }
}
