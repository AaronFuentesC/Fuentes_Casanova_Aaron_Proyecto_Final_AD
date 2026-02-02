package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.EntrenadorRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EntrenadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.EntrenadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Entrenadores", description = "Gestión de entrenadores de equipos de fútbol")
@RestController
@RequiredArgsConstructor
public class EntrenadorController {
    private final EntrenadorService entrenadorService;

    @GetMapping("/entrenadores")
    public ResponseEntity<List<EntrenadorResponse>> listaEntrenadores() {
        List<EntrenadorResponse> entrenadores = entrenadorService.findAll();
        return ResponseEntity.ok(entrenadores);
    }

    @GetMapping("/entrenadores/{id}")
    public ResponseEntity<EntrenadorResponse> obtenerEntrenadorPorId(@PathVariable Long id) {
        EntrenadorResponse entrenador = entrenadorService.findById(id);
        return ResponseEntity.ok(entrenador);
    }
    @PostMapping("/entrenadores")
    public ResponseEntity<EntrenadorResponse> crearEntrenador(@Valid @RequestBody EntrenadorRequest entrenadorRequest) {
        EntrenadorResponse nuevoEntrenador = entrenadorService.create(entrenadorRequest);
        return ResponseEntity.ok(nuevoEntrenador);
    }

    @PutMapping("entrenadores/{id}")
    public ResponseEntity<EntrenadorResponse> actualizarEntrenador(@PathVariable Long id,@Valid @RequestBody EntrenadorRequest entrenadorDetails) {
        EntrenadorResponse entrenadorActualizado = entrenadorService.update(id, entrenadorDetails);
        return ResponseEntity.ok(entrenadorActualizado);
    }

    @DeleteMapping("/entrenadores/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id) {
        entrenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
