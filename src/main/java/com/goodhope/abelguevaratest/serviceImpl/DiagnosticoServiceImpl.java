package com.goodhope.abelguevaratest.serviceImpl;

import com.goodhope.abelguevaratest.entity.Diagnostico;
import com.goodhope.abelguevaratest.repository.DiagnosticoRepository;
import com.goodhope.abelguevaratest.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
