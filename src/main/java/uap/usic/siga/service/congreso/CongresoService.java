package uap.usic.siga.service.congreso;

import uap.usic.siga.domain.congreso.*;

import java.util.List;
import java.util.Optional;

public interface CongresoService {

    List<CongresoUap> listarCongresos();

    CongresoUap guardarCongreso(CongresoUap congreso);

    List<CngTiposCongresistas> listarTiposCongresistas();

    CngTiposCongresistas guardarTipoCongresista(CngTiposCongresistas tipo);

    List<CngTiposCargos> listarTiposCargos();

    CngTiposCargos guardarTipoCargo(CngTiposCargos tipo);

    List<CngTiposComisiones> listarTiposComisiones();

    CngTiposComisiones guardarTipoComision(CngTiposComisiones tipo);

    List<CngCongresistas> listarCongresistas();

    CngCongresistas guardarCongresista(CngCongresistas congresista);

    List<CngMesaDirectiva> listarMesasDirectivas();

    CngMesaDirectiva guardarMesaDirectiva(CngMesaDirectiva mesa);

    List<CngComisiones> listarComisiones();

    CngComisiones guardarComision(CngComisiones comision);
}
