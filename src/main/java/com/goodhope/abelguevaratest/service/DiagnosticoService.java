package com.goodhope.abelguevaratest.service;

import com.goodhope.abelguevaratest.entity.Diagnostico;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos de servicio para la entidad {@link Diagnostico}.
 * <p>
 * Proporciona operaciones CRUD y funcionalidades adicionales, como el filtrado de diagnósticos,
 * para interactuar con la capa de persistencia.
 * </p>
 */
public interface DiagnosticoService {
    List<Diagnostico> listAll();
    Diagnostico save(Diagnostico diagnostico);
    Diagnostico update(Diagnostico diagnostico);
    void delete(Integer id);
    Optional<Diagnostico> findById(Integer id);
    List<Diagnostico> filterDiagnosis(String dsDiagnostico, String tpDiagnostico);
}
