package com.goodhope.abelguevaratest.dto;

import jakarta.validation.constraints.*;

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
public class DiagnosticoPatchRequest {

    @Size(max = 255, message = "Máximo 255 caracteres")
    private String dsDiagnostico;

    @Pattern(regexp = "[12]", message = "El tipo de diagnóstico debe ser 1 o 2")
    private String tpDiagnostico;

    // Getters y Setters
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