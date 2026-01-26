package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.PartidoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.PartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartidoController {

    private final PartidoService partidoService;

    @GetMapping("/partidos")
    public ResponseEntity<List<PartidoResponse>> getAll() {
        List<PartidoResponse> partidos = partidoService.findAll();
        return ResponseEntity.ok(partidos);
    }

    @GetMapping("/partidos/{id}")
    public ResponseEntity<PartidoResponse> obtenerPartidoPorId(@PathVariable Long id) {
        PartidoResponse partido = partidoService.findById(id);
        return ResponseEntity.ok(partido);
    }

    @PostMapping("/partidos")
    public ResponseEntity<PartidoResponse> crearPartido(@RequestBody PartidoRequest partidoRequest) {
        PartidoResponse nuevoPartido = partidoService.create(partidoRequest);
        return ResponseEntity.ok(nuevoPartido);
    }

    @PutMapping("/partidos/{id}")
    public ResponseEntity<PartidoResponse> actualizarPartido(@RequestBody PartidoRequest partidoRequest, @PathVariable Long id) {
        PartidoResponse partidoActualizado = partidoService.update(id, partidoRequest);
        return ResponseEntity.ok(partidoActualizado);
    }

    @DeleteMapping("/partidos/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable Long id) {
        partidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
