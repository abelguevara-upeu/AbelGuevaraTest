package com.goodhope.abelguevaratest.serviceImpl;

import com.goodhope.abelguevaratest.entity.Diagnostico;
import com.goodhope.abelguevaratest.repository.DiagnosticoRepository;
import com.goodhope.abelguevaratest.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link DiagnosticoService} para gestionar las operaciones relacionadas
 * con la entidad {@link Diagnostico}.
 * <p>
 * Esta clase utiliza el repositorio {@link DiagnosticoRepository} para realizar operaciones CRUD
 * y para aplicar filtros en la búsqueda de diagnósticos.
 * </p>
 */
@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    @Autowired
    private DiagnosticoRepository repository;

    @Override
    public List<Diagnostico> listAll() {
        return repository.findAll();
    }

    @Override
    public Diagnostico save(Diagnostico diagnostico) {
        return repository.save(diagnostico);
    }

    @Override
    public Diagnostico update(Diagnostico diagnostico) {
        return repository.save(diagnostico);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Diagnostico> findById(Integer id) {
        return repository.findById(id);
    }

    /**
     * Filtra los diagnósticos en base a la descripción y/o el tipo.
     * <p>
     * Si se proporcionan ambos parámetros, se filtra por descripción y tipo. Si sólo se proporciona
     * uno de ellos, se filtra en base al parámetro suministrado. Si ninguno se proporciona, se retorna
     * la lista completa de diagnósticos.
     * </p>
     *
     * @param dsDiagnostico Descripción parcial del diagnóstico para filtrar (opcional).
     * @param tpDiagnostico Tipo del diagnóstico para filtrar (opcional).
     * @return Lista de diagnósticos que cumplen con los criterios de filtrado.
     */
    @Override
    public List<Diagnostico> filterDiagnosis(String dsDiagnostico, String tpDiagnostico) {
        List<Diagnostico> diagnosticos;
        if (dsDiagnostico != null && tpDiagnostico != null) {
            diagnosticos = repository.findByDsDiagnosticoContainingIgnoreCaseAndTpDiagnostico(dsDiagnostico, tpDiagnostico);
        } else if (dsDiagnostico != null) {
            diagnosticos = repository.findByDsDiagnosticoContainingIgnoreCase(dsDiagnostico);
        } else if (tpDiagnostico != null) {
            diagnosticos = repository.findByTpDiagnostico(tpDiagnostico);
        } else {
            diagnosticos = repository.findAll();
        }
        return diagnosticos;
    }
}
