package uap.usic.siga.service.caja.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.caja.*;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.caja.*;
import uap.usic.siga.service.caja.SacArchivoService;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión del sistema de archivo contable
 * Proporciona lógica de negocio para carpetas, estantes y organización física
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Slf4j
@Service("sacArchivoService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SacArchivoServiceImpl implements SacArchivoService {

    private final SacEstantesRepository estantesRepository;
    private final SacCarpetasRepository carpetasRepository;
    private final SacTiposCarpetasRepository tiposCarpetasRepository;
    private final SacTiposPagosRepository tiposPagosRepository;
    private final SacTiposComprobantesRepository tiposComprobantesRepository;
    private final SacEstadosComprobantesRepository estadosComprobantesRepository;

    // ==================== ESTANTES ====================

    @Override
    public List<SacEstantes> listarEstantes() {
        log.debug("Listando todos los estantes");
        return estantesRepository.findAll();
    }

    @Override
    @Transactional
    public SacEstantes registrarEstante(SacEstantes estante) {
        log.info("Registrando nuevo estante: {}", estante.getCodigo());

        if (estante == null) {
            throw new ValidacionException("El estante no puede ser nulo");
        }

        if (estante.getCodigo() == null || estante.getCodigo().trim().isEmpty()) {
            throw new ValidacionException("El código del estante es obligatorio");
        }

        // Verificar que no exista un estante con el mismo código
        Optional<SacEstantes> estanteExistente = estantesRepository.findByCodigo(estante.getCodigo());
        if (estanteExistente.isPresent()) {
            throw new ValidacionException("Ya existe un estante con el código: " + estante.getCodigo());
        }

        // Establecer estado activo por defecto
        if (estante.getIdEstado() == null) {
            estante.setIdEstado("A");
        }

        SacEstantes estanteGuardado = estantesRepository.save(estante);
        log.info("Estante registrado exitosamente con ID: {}", estanteGuardado.getIdEstante());

        return estanteGuardado;
    }

    @Override
    public Optional<SacEstantes> buscarEstantePorCodigo(String codigo) {
        log.debug("Buscando estante por código: {}", codigo);

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ValidacionException("El código del estante no puede estar vacío");
        }

        return estantesRepository.findByCodigo(codigo);
    }

    // ==================== CARPETAS ====================

    @Override
    public List<SacCarpetas> listarCarpetasPorEstante(Long idEstante) {
        log.debug("Listando carpetas por estante ID: {}", idEstante);

        if (idEstante == null) {
            throw new ValidacionException("El ID del estante es requerido");
        }

        return carpetasRepository.findBySacEstantes_IdEstante(idEstante);
    }

    @Override
    public List<SacCarpetas> listarCarpetasPorGestion(Integer gestion) {
        log.debug("Listando carpetas por gestión: {}", gestion);

        if (gestion == null) {
            throw new ValidacionException("La gestión es requerida");
        }

        return carpetasRepository.findByGestion(gestion);
    }

    @Override
    @Transactional
    public SacCarpetas registrarCarpeta(SacCarpetas carpeta) {
        log.info("Registrando nueva carpeta: {}", carpeta.getCodigo());

        if (carpeta == null) {
            throw new ValidacionException("La carpeta no puede ser nula");
        }

        if (carpeta.getCodigo() == null || carpeta.getCodigo().trim().isEmpty()) {
            throw new ValidacionException("El código de la carpeta es obligatorio");
        }

        if (carpeta.getGestion() == null) {
            throw new ValidacionException("La gestión es obligatoria");
        }

        // Verificar que no exista una carpeta con el mismo código
        Optional<SacCarpetas> carpetaExistente = carpetasRepository.findByCodigo(carpeta.getCodigo());
        if (carpetaExistente.isPresent()) {
            throw new ValidacionException("Ya existe una carpeta con el código: " + carpeta.getCodigo());
        }

        // Establecer estado activo por defecto
        if (carpeta.getIdEstado() == null) {
            carpeta.setIdEstado("A");
        }

        SacCarpetas carpetaGuardada = carpetasRepository.save(carpeta);
        log.info("Carpeta registrada exitosamente con ID: {}", carpetaGuardada.getIdCarpeta());

        return carpetaGuardada;
    }

    @Override
    public Optional<SacCarpetas> buscarCarpetaPorCodigo(String codigo) {
        log.debug("Buscando carpeta por código: {}", codigo);

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ValidacionException("El código de la carpeta no puede estar vacío");
        }

        return carpetasRepository.findByCodigo(codigo);
    }

    // ==================== CATÁLOGOS ====================

    @Override
    public List<SacTiposCarpetas> listarTiposCarpetas() {
        log.debug("Listando todos los tipos de carpetas");
        return tiposCarpetasRepository.findAll();
    }

    @Override
    public List<SacTiposPagos> listarTiposPagos() {
        log.debug("Listando todos los tipos de pagos");
        return tiposPagosRepository.findAll();
    }

    @Override
    public List<SacTiposComprobantes> listarTiposComprobantes() {
        log.debug("Listando todos los tipos de comprobantes");
        return tiposComprobantesRepository.findAll();
    }

    @Override
    public List<SacEstadosComprobantes> listarEstadosComprobantes() {
        log.debug("Listando todos los estados de comprobantes");
        return estadosComprobantesRepository.findAll();
    }
}
