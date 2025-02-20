package com.goodhope.abelguevaratest.repository;

import com.goodhope.abelguevaratest.entity.Diagnostico;
import jdk.jshell.Diag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repositorio para la entidad {@link Diagnostico}.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas,
 * incluyendo filtrado por tipo y descripción.
 * </p>
 */
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Integer> {

    /**
     * Filtra los diagnósticos por el tipo proporcionado.
     *
     * @param tpDiagnostico Tipo del diagnóstico a filtrar.
     * @return Lista de diagnósticos que coinciden con el tipo especificado.
     */
    List<Diagnostico> findByTpDiagnostico(String tpDiagnostico);

    /**
     * Filtra los diagnósticos cuya descripción contiene la cadena especificada,
     * ignorando mayúsculas y minúsculas.
     *
     * @param dsDiagnostico Cadena a buscar en la descripción del diagnóstico.
     * @return Lista de diagnósticos cuya descripción contiene la cadena proporcionada.
     */
    List<Diagnostico> findByDsDiagnosticoContainingIgnoreCase(String dsDiagnostico);

    /**
     * Filtra los diagnósticos por descripción y tipo.
     * <p>
     * Este método realiza una búsqueda en la que la descripción del diagnóstico
     * contiene la cadena especificada (ignorando mayúsculas y minúsculas) y el
     * tipo coincide exactamente con el valor proporcionado.
     * </p>
     *
     * @param dsDiagnostico Cadena a buscar en la descripción del diagnóstico.
     * @param tpDiagnostico Tipo del diagnóstico a filtrar.
     * @return Lista de diagnósticos que cumplen con ambos criterios.
     */
    List<Diagnostico> findByDsDiagnosticoContainingIgnoreCaseAndTpDiagnostico(
            @Param("dsDiagnostico") String dsDiagnostico,
            @Param("tpDiagnostico") String tpDiagnostico
    );
}
