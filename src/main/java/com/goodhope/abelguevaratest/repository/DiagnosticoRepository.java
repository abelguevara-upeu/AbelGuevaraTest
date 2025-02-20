package com.goodhope.abelguevaratest.repository;

import com.goodhope.abelguevaratest.entity.Diagnostico;
import jdk.jshell.Diag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Integer> {
    // Filtrar por tipo
    List<Diagnostico> findByTpDiagnostico(String tpDiagnostico);
    // Filtrar por diagnostico
    List<Diagnostico> findByDsDiagnosticoContainingIgnoreCase(String dsDiagnostico);
    // Filtrar por tipo y diagnostico
    List<Diagnostico> findByDsDiagnosticoContainingIgnoreCaseAndTpDiagnostico(
            @Param("dsDiagnostico") String dsDiagnostico,
            @Param("tpDiagnostico") String tpDiagnostico
    );
}
