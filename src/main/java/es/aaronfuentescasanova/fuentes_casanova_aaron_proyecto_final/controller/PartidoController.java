package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.controller;


import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.request.PartidoRequest;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.JugadorResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.dto.response.PartidoResponse;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.service.implementacion.PartidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Partidos", description = "Gestión de partidos de fútbol jugados entre 2 equipos")
@RestController
@RequiredArgsConstructor
public class PartidoController {

    private final PartidoService partidoService;


    @Operation(
            summary = "Listado de partidos",
            description = "Devuelve todos los partidos"
    )
    @GetMapping("/partidos")
    public ResponseEntity<List<PartidoResponse>> getAll() {
        List<PartidoResponse> partidos = partidoService.findAll();
        return ResponseEntity.ok(partidos);
    }

    @Operation(
            summary = "Obtener un partido",
            description = "Devuelve un partido con un id concreto"
    )
    @GetMapping("/partidos/{id}")
    public ResponseEntity<PartidoResponse> obtenerPartidoPorId(@PathVariable Long id) {
        PartidoResponse partido = partidoService.findById(id);
        return ResponseEntity.ok(partido);
    }


    @Operation(
            summary = "Creación de partidos",
            description = "Crea un nuevo partido de cero entre 2 equipos diferentes"
    )
    @PostMapping("/partidos")
    public ResponseEntity<PartidoResponse> crearPartido(@Valid @RequestBody PartidoRequest partidoRequest) {
        PartidoResponse nuevoPartido = partidoService.create(partidoRequest);
        return ResponseEntity.ok(nuevoPartido);
    }


    @Operation(
            summary = "Actualización de un partido",
            description = "Actualiza un partido para cambiar los datos"
    )
    @PutMapping("/partidos/{id}")
    public ResponseEntity<PartidoResponse> actualizarPartido(@Valid @RequestBody PartidoRequest partidoRequest, @PathVariable Long id) {
        PartidoResponse partidoActualizado = partidoService.update(id, partidoRequest);
        return ResponseEntity.ok(partidoActualizado);
    }


    @Operation(
            summary = "Eliminación de un partido",
            description = "Eliminación de un partido con un id concreto"
    )
    @DeleteMapping("/partidos/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable Long id) {
        partidoService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Listado de partidos",
            description = "Devuelve todos los partidos con paginación"
    )
    @GetMapping("/partidos/paginados")
    public ResponseEntity<Page<PartidoResponse>> listarPartidosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PartidoResponse> partidos = partidoService.findAll(pageable);
        return ResponseEntity.ok(partidos);
    }

}
