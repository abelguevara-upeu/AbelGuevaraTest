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

    /**
     * Obtiene la lista de todos los diagnósticos disponibles.
     *
     * @return ResponseEntity que contiene una respuesta con la lista de objetos {@link DiagnosticoResponse},
     *         el código de estado HTTP y un mensaje.
     */
    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<DiagnosticoResponse>>> listAll() {
        List<DiagnosticoResponse> diagnosticos = service.listAll().stream()
                .map(DiagnosticoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", diagnosticos));
    }

    /**
     * Guarda un nuevo diagnóstico en la base de datos
     *
     * @param request Objeto {@link DiagnosticoRequest} contiene la información a guardar.
     * @return ResponseEntity que contiene el objeto {@link ApiResponse} que contiene el diagnóstico guardado,
     *         el código de estado HTTP y un mensaje. Además, incluye la URI del recurso creado en la cabecera "Location".
     */
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

    /**
     * Actualiza un diagnóstico existente en la base de datos.
     *
     * @param id Identificador del diagnóstico a actualizar.
     * @param request Objeto {@link DiagnosticoRequest} que contiene los nuevos datos del diagnóstico.
     * @return ResponseEntity que contiene un objeto {@link ApiResponse} con el diagnóstico actualizado,
     *         el código de estado HTTP y un mensaje de confirmación.
     */
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

    /**
     * Elimina un diagnóstico por su identificador.
     *
     * @param id Identificador del diagnóstico a eliminar.
     * @return ResponseEntity que contiene un objeto {@link ApiResponse} con el diagnóstico eliminado (si existía),
     *         el código de estado HTTP y un mensaje indicando el resultado de la operación.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<DiagnosticoResponse>> delete(@PathVariable Integer id) {
        DiagnosticoResponse temp = service.findById(id).map(DiagnosticoMapper::toResponse).orElse(null);
        service.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Diagnóstico " + id + " no existe o ha sido eliminado permanentemente",
                        temp
                )
        );
    }

    /**
     * Realiza una actualización parcial de un diagnóstico, modificando únicamente los campos proporcionados en la solicitud.
     * <p>
     * Si el diagnóstico especificado por el {@code id} no existe, se devuelve una respuesta con el estado HTTP 404 (NOT FOUND)
     * y un mensaje de error.
     * </p>
     *
     * @param id Identificador del diagnóstico a actualizar.
     * @param request Objeto {@link DiagnosticoPatchRequest} que contiene los datos para la actualización parcial.
     * @return ResponseEntity que contiene un objeto {@link ApiResponse} con el diagnóstico actualizado o, en caso de no encontrarse,
     *         una respuesta de error con el estado HTTP 404.
     */
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

    /**
     * Busca un diagnóstico por su identificador.
     *
     * @param id Identificador del diagnóstico a buscar.
     * @return ResponseEntity que contiene un objeto {@link ApiResponse} con el diagnóstico encontrado en caso de éxito,
     *         o una respuesta con el estado HTTP 404 (Not Found) si el diagnóstico no existe.
     */
    @GetMapping("/searchById/{id}")
    public ResponseEntity<ApiResponse<DiagnosticoResponse>> searchById(@PathVariable Integer id) {
        return service.findById(id)
                .map(d -> ResponseEntity.ok(
                        new ApiResponse<>(HttpStatus.OK.value(), "OK", DiagnosticoMapper.toResponse(d))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Filtra diagnósticos según los parámetros especificados.
     * <p>
     * Este método permite buscar diagnósticos aplicando filtros opcionales:
     * </p>
     * <ul>
     *   <li><b>dsDiagnostico</b>: Descripción del diagnóstico. Si se proporciona, se utiliza para filtrar la búsqueda.</li>
     *   <li><b>tpDiagnostico</b>: Tipo de diagnóstico, que debe cumplir con el patrón "[12]".</li>
     * </ul>
     * <p>
     * Si la búsqueda no arroja resultados, se devuelve una respuesta con el estado HTTP 404 y un mensaje indicativo;
     * de lo contrario, se retorna una respuesta con el estado HTTP 200 y la lista de diagnósticos encontrados.
     * </p>
     *
     * @param dsDiagnostico (Opcional) La descripción del diagnóstico para aplicar como filtro.
     * @param tpDiagnostico (Opcional) El tipo de diagnóstico, que debe ser "1" o "2".
     * @return ResponseEntity que contiene un objeto {@link ApiResponse} con la lista de diagnósticos filtrados,
     *         o un mensaje de error en caso de no encontrar resultados.
     */
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
