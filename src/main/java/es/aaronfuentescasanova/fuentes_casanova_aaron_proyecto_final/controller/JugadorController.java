package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.JugadorRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.JugadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Jugadores", description = "Gestión de jugadores de los equipos de fútbol")
@RestController
@RequiredArgsConstructor

public class JugadorController {
    private final JugadorService jugadorService;

    @GetMapping("/jugadores")
    public ResponseEntity<List<JugadorResponse>> listarJugadores() {
        List<JugadorResponse> jugadores = jugadorService.findAll();
        return ResponseEntity.ok((jugadores));
    }

    @GetMapping("/jugadores/{id}")
    public ResponseEntity<JugadorResponse> getJugadorPorId(@PathVariable Long id) {
        JugadorResponse jugador = jugadorService.findById(id);
        return ResponseEntity.ok(jugador);
    }

    @PostMapping("/jugadores")
    public ResponseEntity<JugadorResponse> crearJugador(@RequestBody JugadorRequest jugadorRequest) {
        JugadorResponse nuevoJugador = jugadorService.create(jugadorRequest);
        return ResponseEntity.ok(nuevoJugador);
    }

    @PutMapping("/jugadores/{id}")
    public ResponseEntity<JugadorResponse> actualizarJugador(@PathVariable Long id ,@RequestBody JugadorRequest jugadorRequest) {
        JugadorResponse jugadorActualizado = jugadorService.update(id, jugadorRequest);
        return ResponseEntity.ok(jugadorActualizado);
    }

    @DeleteMapping("/jugadores/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/jugadores/paginados/{nombre}")
    public ResponseEntity<Page<JugadorResponse>> listarJugadoresPaginadosNombre(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String nombre
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JugadorResponse> jugadores = jugadorService.findJugadorPageable(pageable,nombre);
        return ResponseEntity.ok(jugadores);
    }

    @GetMapping("/jugadores/paginados")
    public ResponseEntity<Page<JugadorResponse>> listarJugadoresPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JugadorResponse> jugadores = jugadorService.findAll(pageable);
        return ResponseEntity.ok(jugadores);
    }

}
