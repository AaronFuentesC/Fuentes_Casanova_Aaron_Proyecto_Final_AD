package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.EquipoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.EquipoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.EquipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Equipos", description = "Gestión de los partidos de fútbol jugados")
@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoService equipoService;

    @GetMapping("/equipos")
    public ResponseEntity<List<EquipoResponse>> listarEquipos() {
        List<EquipoResponse> equipos = equipoService.findAll();
        return ResponseEntity.ok((equipos));
    }
    @GetMapping("/equipos/{id}")
    public ResponseEntity<EquipoResponse> obtenerEquipoPorId(@PathVariable Long id) {
        EquipoResponse equipo = equipoService.findById(id);
        return ResponseEntity.ok(equipo);
    }
    @PostMapping("/equipos")
    public ResponseEntity<EquipoResponse> crearEquipo(@Valid @RequestBody EquipoRequest equipoRequest) {
        EquipoResponse nuevoEquipo = equipoService.create(equipoRequest);
        return ResponseEntity.ok(nuevoEquipo);
    }

    @PutMapping("equipos/{id}")
    public ResponseEntity<EquipoResponse> actualizarEquipo(@PathVariable Long id, @RequestBody EquipoRequest equipoRequest) {
        EquipoResponse equipoActualizado = equipoService.update(id, equipoRequest);
        return ResponseEntity.ok(equipoActualizado);
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
