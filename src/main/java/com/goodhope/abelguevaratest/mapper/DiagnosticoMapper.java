package com.goodhope.abelguevaratest.mapper;

import com.goodhope.abelguevaratest.dto.DiagnosticoRequest;
import com.goodhope.abelguevaratest.dto.DiagnosticoResponse;
import com.goodhope.abelguevaratest.entity.Diagnostico;

public class DiagnosticoMapper {

    public static Diagnostico toEntity(DiagnosticoRequest request) {
        Diagnostico entity = new Diagnostico();
        entity.setDsDiagnostico(request.getDsDiagnostico());
        entity.setTpDiagnostico(request.getTpDiagnostico());
        return entity;
    }
    public static DiagnosticoResponse toResponse(Diagnostico entity) {
        DiagnosticoResponse response = new DiagnosticoResponse();
        response.setCdDiagnostico(entity.getCdDiagnostico());
        response.setDsDiagnostico(entity.getDsDiagnostico());
        response.setTpDiagnostico(entity.getTpDiagnostico());
        return response;
    }
}

