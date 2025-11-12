package uap.usic.siga.service.caja.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.caja.CjaGastosEjecutados;
import uap.usic.siga.dto.*;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.caja.*;
import uap.usic.siga.service.caja.CajaReportesService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementación del servicio de generación de reportes de caja
 * Proporciona lógica de negocio para consultas complejas y reportes financieros
 *
 * @author Sistema de Caja - USIC
 */
@Slf4j
@Service("cajaReportesService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CajaReportesServiceImpl implements CajaReportesService {

    private final CjaGastosEjecutadosRepository gastosEjecutadosRepository;
    private final CjaTiposClasificacionesRepository tiposClasificacionesRepository;
    private final CjaGastosClasificacionesRepository gastosClasificacionesRepository;

    @Override
    public CjaGastosEjecutadosResponse generarReporteGastoEjecutado(Long idGastoEjecutado) {
        log.info("Generando reporte de gasto ejecutado ID: {}", idGastoEjecutado);

        if (idGastoEjecutado == null) {
            throw new ValidacionException("El ID del gasto ejecutado es requerido");
        }

        CjaGastosEjecutados gasto = gastosEjecutadosRepository.findById(idGastoEjecutado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Gasto ejecutado no encontrado con ID: " + idGastoEjecutado));

        CjaGastosEjecutadosResponse response = new CjaGastosEjecutadosResponse();
        response.setIdGastoEjecutado(gasto.getIdGastoEjecutado());
        response.setMonto(gasto.getMonto());
        response.setFecGasto(gasto.getFecGasto());
        response.setDescripcion(gasto.getDescripcion());
        response.setGlosa(gasto.getGlosa());

        if (gasto.getCjaIngresos() != null) {
            response.setIdIngreso(gasto.getCjaIngresos().getIdCjaIngreso());
            response.setMontoIngreso(gasto.getCjaIngresos().getMonto());
        }

        if (gasto.getCjaTiposGastos() != null) {
            response.setTipoGasto(gasto.getCjaTiposGastos().getDescripcion());
        }

        if (gasto.getPersonas() != null) {
            response.setNombrePersona(gasto.getPersonas().getNombres() + " " +
                    gasto.getPersonas().getPrimerApellido());
        }

        log.info("Reporte generado exitosamente para gasto ID: {}", idGastoEjecutado);
        return response;
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionConTotales(
            Long idUsuario, Long idIngreso, Date fecIngreso, Date fecGasto) {
        log.debug("Listando tipos de clasificación con totales - Usuario: {}, Ingreso: {}",
                idUsuario, idIngreso);

        if (idUsuario == null) {
            throw new ValidacionException("El ID del usuario es requerido");
        }

        // Consulta personalizada para obtener totales por tipo de clasificación
        List<CjaTiposClasificacionesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findTotalesPorTipoClasificacion(idUsuario, idIngreso, fecIngreso, fecGasto);

        for (Object[] fila : datos) {
            CjaTiposClasificacionesResponse response = new CjaTiposClasificacionesResponse();
            response.setIdTipoClasificacion((Long) fila[0]);
            response.setDescripcion((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionPorIngreso(
            Long idUsuario, Long idIngreso) {
        log.debug("Listando gastos por clasificación - Usuario: {}, Ingreso: {}",
                idUsuario, idIngreso);

        if (idUsuario == null || idIngreso == null) {
            throw new ValidacionException("El ID del usuario e ingreso son requeridos");
        }

        List<CjaGastosClasificacionesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findGastosPorClasificacion(idUsuario, idIngreso);

        for (Object[] fila : datos) {
            CjaGastosClasificacionesResponse response = new CjaGastosClasificacionesResponse();
            response.setIdClasificacion((Long) fila[0]);
            response.setDescripcion((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            response.setCantidadGastos((Long) fila[3]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaUnidadesFuncionalesResponse> listarTotalesGastosPorUnidadesFuncionales(
            Long idDireccionFuncional, Integer gestion) {
        log.debug("Listando totales de gastos por unidades funcionales - Dirección: {}, Gestión: {}",
                idDireccionFuncional, gestion);

        if (idDireccionFuncional == null || gestion == null) {
            throw new ValidacionException("El ID de dirección funcional y gestión son requeridos");
        }

        List<CjaUnidadesFuncionalesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findTotalesPorUnidadFuncional(idDireccionFuncional, gestion);

        for (Object[] fila : datos) {
            CjaUnidadesFuncionalesResponse response = new CjaUnidadesFuncionalesResponse();
            response.setIdUnidadFuncional((Long) fila[0]);
            response.setNombreUnidad((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaDireccionesFuncionalesResponse> listarTotalesGastosPorDireccionesFuncionales(
            Long idSede, Integer gestion) {
        log.debug("Listando totales de gastos por direcciones funcionales - Sede: {}, Gestión: {}",
                idSede, gestion);

        if (idSede == null || gestion == null) {
            throw new ValidacionException("El ID de sede y gestión son requeridos");
        }

        List<CjaDireccionesFuncionalesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findTotalesPorDireccionFuncional(idSede, gestion);

        for (Object[] fila : datos) {
            CjaDireccionesFuncionalesResponse response = new CjaDireccionesFuncionalesResponse();
            response.setIdDireccionFuncional((Long) fila[0]);
            response.setNombreDireccion((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionPorIngreso(Long idIngreso) {
        log.debug("Listando tipos de clasificación por ingreso ID: {}", idIngreso);

        if (idIngreso == null) {
            throw new ValidacionException("El ID del ingreso es requerido");
        }

        List<CjaTiposClasificacionesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findTiposClasificacionPorIngreso(idIngreso);

        for (Object[] fila : datos) {
            CjaTiposClasificacionesResponse response = new CjaTiposClasificacionesResponse();
            response.setIdTipoClasificacion((Long) fila[0]);
            response.setDescripcion((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorTipoClasificacionUnidad(
            Long idUnidadFuncional, Integer gestion) {
        log.debug("Listando totales por tipo de clasificación y unidad - Unidad: {}, Gestión: {}",
                idUnidadFuncional, gestion);

        if (idUnidadFuncional == null || gestion == null) {
            throw new ValidacionException("El ID de unidad funcional y gestión son requeridos");
        }

        List<CjaTiposClasificacionesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findTotalesPorTipoClasificacionYUnidad(idUnidadFuncional, gestion);

        for (Object[] fila : datos) {
            CjaTiposClasificacionesResponse response = new CjaTiposClasificacionesResponse();
            response.setIdTipoClasificacion((Long) fila[0]);
            response.setDescripcion((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorPartidasDirecciones(
            Long idDireccionFuncional, Integer gestion) {
        log.debug("Listando totales por partidas y direcciones - Dirección: {}, Gestión: {}",
                idDireccionFuncional, gestion);

        if (idDireccionFuncional == null || gestion == null) {
            throw new ValidacionException("El ID de dirección funcional y gestión son requeridos");
        }

        List<CjaTiposClasificacionesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findTotalesPorPartidasYDirecciones(idDireccionFuncional, gestion);

        for (Object[] fila : datos) {
            CjaTiposClasificacionesResponse response = new CjaTiposClasificacionesResponse();
            response.setIdTipoClasificacion((Long) fila[0]);
            response.setDescripcion((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            response.setPartida((String) fila[3]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGeneralesGastosPorPartidaSede(
            Long idSede, Integer gestion) {
        log.debug("Listando totales generales por partida y sede - Sede: {}, Gestión: {}",
                idSede, gestion);

        if (idSede == null || gestion == null) {
            throw new ValidacionException("El ID de sede y gestión son requeridos");
        }

        List<CjaTiposClasificacionesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findTotalesGeneralesPorPartidaYSede(idSede, gestion);

        for (Object[] fila : datos) {
            CjaTiposClasificacionesResponse response = new CjaTiposClasificacionesResponse();
            response.setPartida((String) fila[0]);
            response.setDescripcion((String) fila[1]);
            response.setTotalGastado((Double) fila[2]);
            resultados.add(response);
        }

        return resultados;
    }

    @Override
    public List<CjaTiposClasificacionesResponse> buscarMontoMaximoPorSede(Long idSede, Integer gestion) {
        log.debug("Buscando monto máximo por sede ID: {}, gestión: {}", idSede, gestion);

        if (idSede == null || gestion == null) {
            throw new ValidacionException("El ID de sede y gestión son requeridos");
        }

        List<CjaTiposClasificacionesResponse> resultados = new ArrayList<>();

        List<Object[]> datos = gastosEjecutadosRepository
                .findMontoMaximoPorSede(idSede, gestion);

        for (Object[] fila : datos) {
            CjaTiposClasificacionesResponse response = new CjaTiposClasificacionesResponse();
            response.setDescripcion((String) fila[0]);
            response.setTotalGastado((Double) fila[1]);
            response.setMontoMaximo((Double) fila[1]);
            resultados.add(response);
        }

        return resultados;
    }
}
