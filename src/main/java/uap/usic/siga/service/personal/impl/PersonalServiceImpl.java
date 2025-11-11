package uap.usic.siga.service.personal.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.personal.PnlCargos;
import uap.usic.siga.domain.personal.PnlItems;
import uap.usic.siga.domain.personal.PnlPersonalAdministrativos;
import uap.usic.siga.domain.personal.PnlTiposAdministrativos;
import uap.usic.siga.repository.personal.PnlCargosRepository;
import uap.usic.siga.repository.personal.PnlItemsRepository;
import uap.usic.siga.repository.personal.PnlPersonalAdministrativosRepository;
import uap.usic.siga.repository.personal.PnlTiposAdministrativosRepository;
import uap.usic.siga.service.personal.PersonalService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonalServiceImpl implements PersonalService {

    private final PnlCargosRepository cargosRepository;
    private final PnlItemsRepository itemsRepository;
    private final PnlTiposAdministrativosRepository tiposAdministrativosRepository;
    private final PnlPersonalAdministrativosRepository personalAdministrativosRepository;

    public PersonalServiceImpl(
        PnlCargosRepository cargosRepository,
        PnlItemsRepository itemsRepository,
        PnlTiposAdministrativosRepository tiposAdministrativosRepository,
        PnlPersonalAdministrativosRepository personalAdministrativosRepository
    ) {
        this.cargosRepository = cargosRepository;
        this.itemsRepository = itemsRepository;
        this.tiposAdministrativosRepository = tiposAdministrativosRepository;
        this.personalAdministrativosRepository = personalAdministrativosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PnlCargos> listarCargos() {
        return cargosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PnlItems> listarItems() {
        return itemsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PnlTiposAdministrativos> listarTiposAdministrativos() {
        return tiposAdministrativosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PnlPersonalAdministrativos> listarPersonalAdministrativos() {
        return personalAdministrativosRepository.findAll();
    }

    @Override
    public PnlPersonalAdministrativos guardarPersonalAdministrativo(PnlPersonalAdministrativos personalAdministrativo) {
        return personalAdministrativosRepository.save(personalAdministrativo);
    }

    @Override
    public PnlPersonalAdministrativos modificarPersonalAdministrativo(PnlPersonalAdministrativos personalAdministrativo) {
        return personalAdministrativosRepository.save(personalAdministrativo);
    }

    @Override
    public void eliminarPersonalAdministrativo(Long id) {
        personalAdministrativosRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PnlPersonalAdministrativos> buscarPersonalAdministrativoPorId(Long id) {
        return personalAdministrativosRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PnlPersonalAdministrativos> buscarPersonalAdministrativoPorPersonaGestionPeriodo(
        Long idPersona,
        Integer gestion,
        Integer periodo
    ) {
        return personalAdministrativosRepository.findByPersonaAndGestionAndPeriodo(idPersona, gestion, periodo);
    }

    @Override
    public PnlCargos guardarCargo(PnlCargos cargo) {
        return cargosRepository.save(cargo);
    }

    @Override
    public PnlCargos actualizarCargo(PnlCargos cargo) {
        return cargosRepository.save(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PnlCargos> buscarCargoPorId(Long id) {
        return cargosRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PnlCargos> buscarCargoPorNombre(String cargo) {
        return cargosRepository.findByCargo(cargo);
    }

    @Override
    public PnlTiposAdministrativos guardarTipoAdministrativo(PnlTiposAdministrativos tipoAdministrativo) {
        return tiposAdministrativosRepository.save(tipoAdministrativo);
    }

    @Override
    public PnlItems guardarItem(PnlItems item) {
        return itemsRepository.save(item);
    }
}
