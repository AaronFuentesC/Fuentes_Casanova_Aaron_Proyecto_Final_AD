package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.JugadorRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.mappers.JugadorMapper;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Jugador;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.EquipoRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.JugadorRepository;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.interfaces.IJugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JugadorService implements IJugadorService {
    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final JugadorMapper jugadorMapper;
    @Override
    public List<JugadorResponse> findAll() {
        return jugadorRepository.findAll().stream()
                .map(jugadorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JugadorResponse findById(Long id) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        return jugadorMapper.toResponse(jugador);
    }

    @Override
    public JugadorResponse create(JugadorRequest request) {
        Equipo equipo = equipoRepository.findById(request.getId_equipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getId_equipo()));
        Jugador jugador = Jugador.builder()
                .equipo(equipo)
                .nombre(request.getNombre())
                .posicion(request.getPosicion())
                .fechaNacimiento(request.getFechaNacimiento())
                .build();
        Jugador savedJugador = jugadorRepository.save(jugador);
        return jugadorMapper.toResponse(savedJugador);

    }

    @Override
    public JugadorResponse update(Long id, JugadorRequest request) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));
        Equipo equipo = equipoRepository.findById(request.getId_equipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getId_equipo()));
        jugador.setEquipo(equipo);
        jugador.setNombre(request.getNombre());
        jugador.setPosicion(request.getPosicion());
        jugador.setFechaNacimiento(request.getFechaNacimiento());
        Jugador updatedJugador = jugadorRepository.save(jugador);
        return jugadorMapper.toResponse(updatedJugador);
    }

    @Override
    public void delete(Long id) {
        if (!jugadorRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }
        jugadorRepository.deleteById(id);
    }
}
