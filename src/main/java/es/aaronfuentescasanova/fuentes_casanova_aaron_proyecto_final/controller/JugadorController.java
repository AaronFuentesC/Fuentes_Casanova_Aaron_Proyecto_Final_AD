package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.JugadorRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.JugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
