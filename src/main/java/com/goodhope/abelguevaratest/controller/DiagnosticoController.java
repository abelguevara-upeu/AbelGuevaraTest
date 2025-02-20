package com.goodhope.abelguevaratest.controller;

import com.goodhope.abelguevaratest.dto.DiagnosticoPatchRequest;
import com.goodhope.abelguevaratest.dto.DiagnosticoRequest;
import com.goodhope.abelguevaratest.dto.DiagnosticoResponse;
import com.goodhope.abelguevaratest.entity.Diagnostico;
import com.goodhope.abelguevaratest.mapper.DiagnosticoMapper;
import com.goodhope.abelguevaratest.payload.ApiResponse;
import com.goodhope.abelguevaratest.service.DiagnosticoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService service; //Inyección de dependencia

    /*
     *
     *
     */
    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<DiagnosticoResponse>>> listAll() {
        List<DiagnosticoResponse> diagnosticos = service.listAll().stream()
                .map(DiagnosticoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", diagnosticos));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<DiagnosticoResponse>> save( @Valid @RequestBody DiagnosticoRequest request) {
        Diagnostico entity = DiagnosticoMapper.toEntity(request);
        Diagnostico saved = service.save(entity);
        DiagnosticoResponse response = DiagnosticoMapper.toResponse(saved);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getCdDiagnostico())
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "OK", response));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<DiagnosticoResponse>> edit(
            @PathVariable("id") Integer id,
            @Valid @RequestBody DiagnosticoRequest request) {

        Diagnostico entity = DiagnosticoMapper.toEntity(request);
        entity.setCdDiagnostico(id);
        Diagnostico updated = service.update(entity);
        DiagnosticoResponse response = DiagnosticoMapper.toResponse(updated);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Actualizado", response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<DiagnosticoResponse>> delete(@PathVariable Integer id) {
        DiagnosticoResponse temp = service.findById(id).map(DiagnosticoMapper::toResponse).orElse(null);
        service.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        "Diagnóstico " + id + " no existe o ha sido eliminado permanentemente",
                        temp
                )
        );
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<ApiResponse<DiagnosticoResponse>> patch(@PathVariable Integer id, @Valid @RequestBody DiagnosticoPatchRequest request) {
        Optional<Diagnostico> optionalEntity = service.findById(id);
        if (optionalEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            HttpStatus.NOT_FOUND.value(),
                            "Diagnóstico no encontrado",
                            null
                    )
            );
        }
        Diagnostico entity = optionalEntity.get();
        if (request.getDsDiagnostico() != null) {
            entity.setDsDiagnostico(request.getDsDiagnostico());
        }
        if (request.getTpDiagnostico() != null) {
            entity.setTpDiagnostico(request.getTpDiagnostico());
        }
        Diagnostico updated = service.update(entity);
        DiagnosticoResponse response = DiagnosticoMapper.toResponse(updated);
        return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), "Actualizado parcialmente", response));
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<ApiResponse<DiagnosticoResponse>> searchById(@PathVariable Integer id) {
        return service.findById(id)
                .map(d -> ResponseEntity.ok(
                        new ApiResponse<>(HttpStatus.OK.value(), "OK", DiagnosticoMapper.toResponse(d))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<DiagnosticoResponse>>> filterDiagnosis(
            @RequestParam(required = false) String dsDiagnostico,
            @RequestParam(required = false) @Pattern(regexp = "[12]", message = "El tipo debe ser 1 o 2") String tpDiagnostico) {
        List<DiagnosticoResponse> response = service.filterDiagnosis(dsDiagnostico, tpDiagnostico)
                .stream()
                .map(DiagnosticoMapper::toResponse).toList();

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se encontraron resultados", null)
            );
        }

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", response));
    }
}
