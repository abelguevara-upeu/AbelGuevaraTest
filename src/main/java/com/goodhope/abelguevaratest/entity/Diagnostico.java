package com.goodhope.abelguevaratest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "GUEVARA_DIAGNOSTICO")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_DIAGNOSTICO")
    private Integer cdDiagnostico;

    @Column(name = "DS_DIAGNOSTICO")
    private String dsDiagnostico;

    @Column(name = "TP_DIAGNOSTICO", length = 1)
    private String tpDiagnostico;

    public Diagnostico() {}

    public Diagnostico(Integer cdDiagnostico, String dsDiagnostico, String tpDiagnostico) {
        this.cdDiagnostico = cdDiagnostico;
        this.dsDiagnostico = dsDiagnostico;
        this.tpDiagnostico = tpDiagnostico;
    }

    public Integer getCdDiagnostico() {
        return cdDiagnostico;
    }

    public void setCdDiagnostico(Integer cdDiagnostico) {
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

    //Actualizar campos específicos
    public void updateFields(String dsDiagnostico, String tpDiagnostico) {
        if (this.dsDiagnostico != null) {
            this.setDsDiagnostico(this.dsDiagnostico);
        }
        if (this.tpDiagnostico != null) {
            this.setTpDiagnostico(this.tpDiagnostico);
        }
    }
}
