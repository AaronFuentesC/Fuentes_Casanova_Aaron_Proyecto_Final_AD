package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.TorneoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.TorneoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TorneoController {
    private final TorneoService torneoService;

    @GetMapping("/torneos")
    public ResponseEntity<List<TorneoResponse>> getAll() {
        List<TorneoResponse> torneos = torneoService.findAll();
        return ResponseEntity.ok(torneos);
    }

    @GetMapping("/torneos/{id}")
    public ResponseEntity<TorneoResponse> obtenerTorneoPorId(@PathVariable Long id) {
        TorneoResponse torneo = torneoService.findById(id);
        return ResponseEntity.ok(torneo);
    }

    @PostMapping("/torneos")
    public ResponseEntity<TorneoResponse> crearTorneo(@RequestBody TorneoRequest torneoRequest) {
        TorneoResponse nuevoTorneo = torneoService.create(torneoRequest);
        return ResponseEntity.ok(nuevoTorneo);
    }

    @PutMapping("/torneos/{id}")
    public ResponseEntity<TorneoResponse> actualizarTorneo(@RequestBody TorneoRequest torneoRequest, @PathVariable Long id) {
        TorneoResponse torneoActualizado = torneoService.update(id, torneoRequest);
        return ResponseEntity.ok(torneoActualizado);
    }

    @DeleteMapping("/torneos/{id}")
    public ResponseEntity<Void> eliminarTorneo(@PathVariable Long id) {
        torneoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
