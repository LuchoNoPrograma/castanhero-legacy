package uap.usic.siga.service.personal;

import uap.usic.siga.domain.personal.PnlCargos;
import uap.usic.siga.domain.personal.PnlItems;
import uap.usic.siga.domain.personal.PnlPersonalAdministrativos;
import uap.usic.siga.domain.personal.PnlTiposAdministrativos;

import java.util.List;
import java.util.Optional;

public interface PersonalService {

    List<PnlCargos> listarCargos();

    List<PnlItems> listarItems();

    List<PnlTiposAdministrativos> listarTiposAdministrativos();

    List<PnlPersonalAdministrativos> listarPersonalAdministrativos();

    PnlPersonalAdministrativos guardarPersonalAdministrativo(PnlPersonalAdministrativos personalAdministrativo);

    PnlPersonalAdministrativos modificarPersonalAdministrativo(PnlPersonalAdministrativos personalAdministrativo);

    void eliminarPersonalAdministrativo(Long id);

    Optional<PnlPersonalAdministrativos> buscarPersonalAdministrativoPorId(Long id);

    Optional<PnlPersonalAdministrativos> buscarPersonalAdministrativoPorPersonaGestionPeriodo(Long idPersona, Integer gestion, Integer periodo);

    PnlCargos guardarCargo(PnlCargos cargo);

    PnlCargos actualizarCargo(PnlCargos cargo);

    Optional<PnlCargos> buscarCargoPorId(Long id);

    Optional<PnlCargos> buscarCargoPorNombre(String cargo);

    PnlTiposAdministrativos guardarTipoAdministrativo(PnlTiposAdministrativos tipoAdministrativo);

    PnlItems guardarItem(PnlItems item);
}
