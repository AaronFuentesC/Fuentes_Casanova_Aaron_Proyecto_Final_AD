package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.EquipoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.EquipoMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.EquipoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.IEquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EquipoService implements IEquipoService {
    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;
    @Override
    public List<EquipoResponse> findAll() {
        return equipoRepository.findAll().stream()
                .map(equipoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EquipoResponse findById(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        return equipoMapper.toResponse(equipo);
    }

    @Override
    public EquipoResponse create(EquipoRequest request) {
        Equipo equipo = Equipo.builder()
                .nombre(request.getNombre())
                .ciudad(request.getCiudad())
                .pais(request.getPais())
                .estadio(request.getEstadio())
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
        if (!equipoRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }
        equipoRepository.deleteById(id);
    }

}
