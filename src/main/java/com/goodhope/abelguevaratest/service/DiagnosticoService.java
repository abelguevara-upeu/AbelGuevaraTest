package com.goodhope.abelguevaratest.service;

import com.goodhope.abelguevaratest.entity.Diagnostico;

import java.util.List;
import java.util.Optional;

public interface DiagnosticoService {
    List<Diagnostico> listAll();
    Diagnostico save(Diagnostico diagnostico);
    Diagnostico update(Diagnostico diagnostico);
    void delete(Integer id);
    Optional<Diagnostico> findById(Integer id);
    List<Diagnostico> filterDiagnosis(String dsDiagnostico, String tpDiagnostico);
}
