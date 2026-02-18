package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.TorneoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponseLite;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.TorneoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Equipo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.Torneo;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.PartidoService;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.TorneoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "Torneos", description = "Gestión de torneos en los que participan varios equipos de fútbol de diferentes países.")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TorneoController {
    private final TorneoService torneoService;
    private final   PartidoService partidoService;

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
    public ResponseEntity<TorneoResponse> crearTorneo(@Valid @RequestBody TorneoRequest torneoRequest) {
        TorneoResponse nuevoTorneo = torneoService.create(torneoRequest);
        return ResponseEntity.ok(nuevoTorneo);
    }

    @PutMapping("/torneos/{id}")
    public ResponseEntity<TorneoResponse> actualizarTorneo(@Valid @RequestBody TorneoRequest torneoRequest, @PathVariable Long id) {
        TorneoResponse torneoActualizado = torneoService.update(id, torneoRequest);
        return ResponseEntity.ok(torneoActualizado);
    }

    @DeleteMapping("/torneos/{id}")
    public ResponseEntity<Void> eliminarTorneo(@PathVariable Long id) {
        torneoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/torneos/paginados")
    public ResponseEntity<Page<TorneoResponse>> listarTorneosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TorneoResponse> torneos = torneoService.findAll(pageable);
        return ResponseEntity.ok(torneos);
    }
    @GetMapping("/torneos/{id}/equipos")
    public ResponseEntity<List<EquipoResponseLite>> getEquiposPorTorneo(@PathVariable Long id) {
        return ResponseEntity.ok(torneoService.getEquiposByTorneo(id));
    }



    @GetMapping("/torneos/{id}/partidos")
    public ResponseEntity<List<PartidoResponse>> getPartidosByTorneo(@PathVariable Long id) {
        return ResponseEntity.ok(torneoService.getPartidosByTorneo(id));
    }

    @PostMapping("/torneos/{torneoId}/equipos/{equipoId}")
    public ResponseEntity<Void> inscribirEquipo(
            @PathVariable Long torneoId,
            @PathVariable Long equipoId) {

        torneoService.inscribirEquipoEnTorneo(equipoId, torneoId);
        return ResponseEntity.ok().build();
    }


}
