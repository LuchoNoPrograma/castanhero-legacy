package uap.usic.siga.service.electoral;

import uap.usic.siga.domain.electoral.*;

import java.util.List;
import java.util.Optional;

public interface ElectoralService {

    List<EscElecciones> listarElecciones();

    EscElecciones guardarEleccion(EscElecciones eleccion);

    List<EscFrentes> listarFrentes();

    EscFrentes guardarFrente(EscFrentes frente);

    List<EscMesasHabilitadas> listarMesasHabilitadas();

    EscMesasHabilitadas guardarMesaHabilitada(EscMesasHabilitadas mesa);

    List<EscrutinioActas> listarEscrutinioActas();

    EscrutinioActas guardarEscrutinioActa(EscrutinioActas acta);

    List<EscDetalles> listarDetalles();

    EscDetalles guardarDetalle(EscDetalles detalle);

    List<EscResultadosGraficos> listarResultadosGraficos();

    EscResultadosGraficos guardarResultadoGrafico(EscResultadosGraficos resultado);
}
