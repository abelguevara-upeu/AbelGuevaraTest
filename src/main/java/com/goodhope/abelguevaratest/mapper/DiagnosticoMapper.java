package com.goodhope.abelguevaratest.mapper;

import com.goodhope.abelguevaratest.dto.DiagnosticoRequest;
import com.goodhope.abelguevaratest.dto.DiagnosticoResponse;
import com.goodhope.abelguevaratest.entity.Diagnostico;

/**
 * Clase utilitaria para mapear entre objetos de tipo {@link Diagnostico} y sus correspondientes
 * objetos de transferencia de datos: {@link DiagnosticoRequest} y {@link DiagnosticoResponse}.
 * <p>
 * Proporciona métodos estáticos para convertir una solicitud a entidad y una entidad a respuesta.
 * </p>
 */
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

