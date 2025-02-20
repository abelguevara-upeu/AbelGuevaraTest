package com.goodhope.abelguevaratest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Representa la solicitud para crear o actualizar un diagnóstico.
 * <p>
 * Esta clase se utiliza en la capa de DTO para recibir y validar los datos de entrada en las peticiones HTTP.
 * </p>
 * <p>
 * Los campos que contiene son:
 * <ul>
 *   <li>{@code dsDiagnostico}: La descripción del diagnóstico. Es obligatorio y está limitado a 255 caracteres.</li>
 *   <li>{@code tpDiagnostico}: El tipo del diagnóstico, que debe ser "1" (CIE10) o "2" (CIE11).</li>
 * </ul>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiagnosticoRequest {

    @NotBlank(message = "El nombre es obligatorio en este campo")
    @Size(max = 255, message = "Máximo de 255 caracteres")
    private String dsDiagnostico;

    @NotBlank(message = "El tipo de diagnóstico no puede estar vacío ni ser nulo en este campo")
    @Pattern(regexp = "^[12]$", message = "El tipo de diagnostico debe ser 1 (CIE10) o 2 (CIE11)")
    private String tpDiagnostico;

    public DiagnosticoRequest() {}

    public String getDsDiagnostico() {
        return dsDiagnostico;
    }

    public void setDsDiagnostico(String dsDiagnostico) {
        this.dsDiagnostico = dsDiagnostico;
    }

    public String getTpDiagnostico() {
        return tpDiagnostico;
    }

    public void setTpDiagnostico(String tpDiagnostico) {
        this.tpDiagnostico = tpDiagnostico;
    }
}
