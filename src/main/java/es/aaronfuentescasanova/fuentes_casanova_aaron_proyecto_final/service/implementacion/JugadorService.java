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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JugadorService implements IJugadorService {
    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final JugadorMapper jugadorMapper;


    @Transactional(readOnly = true)
    @Override
    public List<JugadorResponse> findAll() {
        return jugadorRepository.findAll().stream()
                .map(jugadorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public JugadorResponse findById(Long id) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));
        return jugadorMapper.toResponse(jugador);
    }

    @Override
    public JugadorResponse create(JugadorRequest request) {
        Equipo equipo = equipoRepository.findById(request.getId_equipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + request.getId_equipo()));
        if (jugadorRepository.countByEquipoId(equipo.getId()) >= 25) {
            throw new RuntimeException("Un equipo no puede tener más de 25 jugadores");
        }
        if (jugadorRepository.existsByEquipoIdAndDorsal(equipo.getId(), request.getDorsal())) {
            throw new RuntimeException("El dorsal ya está ocupado en el equipo");
        }

        if (Period.between(request.getFechaNacimiento(), LocalDate.now()).getYears() < 16) {
            throw new RuntimeException("El jugador debe ser mayor de 16 años");
        }



        Jugador jugador = Jugador.builder()
                .equipo(equipo)
                .nombre(request.getNombre())
                .posicion(request.getPosicion())
                .fechaNacimiento(request.getFechaNacimiento())
                .dorsal(request.getDorsal())
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
        jugador.setDorsal(request.getDorsal());
        Jugador updatedJugador = jugadorRepository.save(jugador);
        return jugadorMapper.toResponse(updatedJugador);
    }

    @Override
    public void delete(Long id) {
        if (!jugadorRepository.existsById(id)) {
            throw new RuntimeException("Jugador no encontrado con id: " + id);
        }
        jugadorRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    @Override
    public Page<JugadorResponse> findJugadorPageable(Pageable pageable,String nombre) {
        return jugadorRepository.findByNombreContainingIgnoreCase(nombre,pageable)
                .map(jugadorMapper::toResponse);
    }

    @Override
    public Page<JugadorResponse> findAll(Pageable pageable) {
        return jugadorRepository.findAll(pageable)
                .map(jugadorMapper::toResponse);
    }

    public List<JugadorResponse> findByEquipoId(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId)
                .stream()
                .map(jugadorMapper::toResponse)
                .toList();
    }



}
