package com.goodhope.abelguevaratest.dto;

import jakarta.validation.constraints.*;

public class DiagnosticoPatchRequest {

    @Size(max = 255, message = "Máximo 255 caracteres")
    private String dsDiagnostico;

    @Pattern(regexp = "[12]", message = "El tipo de diagnóstico debe ser 1 o 2")
    private String tpDiagnostico;

    // Getters y Setters
    public String getDsDiagnostico() { return dsDiagnostico; }
    public void setDsDiagnostico(String dsDiagnostico) { this.dsDiagnostico = dsDiagnostico; }
    public String getTpDiagnostico() { return tpDiagnostico; }
    public void setTpDiagnostico(String tpDiagnostico) { this.tpDiagnostico = tpDiagnostico; }
}