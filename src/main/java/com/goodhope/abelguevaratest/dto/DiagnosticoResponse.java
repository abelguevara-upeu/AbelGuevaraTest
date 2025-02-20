package com.goodhope.abelguevaratest.dto;

/**
 * Representa la respuesta de un diagnóstico.
 * <p>
 * Esta clase encapsula la información básica de un diagnóstico,
 * incluyendo el código, la descripción y el tipo del diagnóstico.
 * </p>
 */
public class DiagnosticoResponse {

    private int cdDiagnostico;
    private String dsDiagnostico;
    private String tpDiagnostico;

    public DiagnosticoResponse() {}

    public int getCdDiagnostico() {
        return cdDiagnostico;
    }

    public void setCdDiagnostico(int cdDiagnostico) {
        this.cdDiagnostico = cdDiagnostico;
    }

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
