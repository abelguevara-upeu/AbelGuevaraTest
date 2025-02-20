package com.goodhope.abelguevaratest.dto;

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
