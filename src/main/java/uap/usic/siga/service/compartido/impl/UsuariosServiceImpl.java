package uap.usic.siga.service.compartido.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.compartido.Roles;
import uap.usic.siga.domain.compartido.Usuarios;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.compartido.RolesRepository;
import uap.usic.siga.repository.compartido.UsuariosRepository;
import uap.usic.siga.service.compartido.UsuariosService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository,
                               RolesRepository rolesRepository,
                               PasswordEncoder passwordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuarios buscarPorId(Long id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Usuarios buscarPorEmail(String email) {
        return usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con email: " + email));
    }

    @Override
    public Usuarios buscarPorUsername(String username) {
        return usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con username: " + username));
    }

    @Override
    public Usuarios buscarPorName(String name) {
        return usuariosRepository.findByName(name)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con name: " + name));
    }

    @Override
    public Optional<Usuarios> buscarPorIdPersona(Long idPersona) {
        return usuariosRepository.buscarPorIdPersona(idPersona);
    }

    @Override
    public List<Usuarios> listarUsuariosActivos() {
        return usuariosRepository.listarUsuariosActivos();
    }

    @Override
    public Page<Usuarios> listarPaginado(Pageable pageable) {
        return usuariosRepository.listarUsuariosActivosPaginado(pageable);
    }

    @Override
    @Transactional
    public Usuarios registrar(Usuarios usuario) {
        validarUsuarioNuevo(usuario);

        if (usuariosRepository.existsByEmail(usuario.getEmail())) {
            throw new ValidacionException("El email ya está registrado: " + usuario.getEmail());
        }

        if (usuario.getUsername() != null && usuariosRepository.existsByUsername(usuario.getUsername())) {
            throw new ValidacionException("El username ya está registrado: " + usuario.getUsername());
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setDateCreated(new Date());
        usuario.setEnabled(true);
        usuario.activar();

        return usuariosRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuarios actualizar(Long id, Usuarios usuarioActualizado) {
        Usuarios usuarioExistente = buscarPorId(id);

        if (!usuarioExistente.getEmail().equals(usuarioActualizado.getEmail())) {
            if (usuariosRepository.existsByEmail(usuarioActualizado.getEmail())) {
                throw new ValidacionException("El email ya está registrado: " + usuarioActualizado.getEmail());
            }
        }

        usuarioExistente.setName(usuarioActualizado.getName());
        usuarioExistente.setSurname(usuarioActualizado.getSurname());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());

        if (usuarioActualizado.getUsername() != null) {
            usuarioExistente.setUsername(usuarioActualizado.getUsername());
        }

        if (usuarioActualizado.getPersonas() != null) {
            usuarioExistente.setPersonas(usuarioActualizado.getPersonas());
        }

        return usuariosRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Usuarios usuario = buscarPorId(id);
        usuario.desactivar();
        usuariosRepository.save(usuario);
    }

    @Override
    @Transactional
    public void activar(Long id) {
        Usuarios usuario = buscarPorId(id);
        usuario.activar();
        usuario.setEnabled(true);
        usuariosRepository.save(usuario);
    }

    @Override
    @Transactional
    public void desactivar(Long id) {
        Usuarios usuario = buscarPorId(id);
        usuario.desactivar();
        usuario.setEnabled(false);
        usuariosRepository.save(usuario);
    }

    @Override
    @Transactional
    public void cambiarContrasena(Long id, String contrasenaActual, String contrasenaNueva) {
        Usuarios usuario = buscarPorId(id);

        if (!passwordEncoder.matches(contrasenaActual, usuario.getPassword())) {
            throw new ValidacionException("La contraseña actual no es correcta");
        }

        if (contrasenaActual.equals(contrasenaNueva)) {
            throw new ValidacionException("La nueva contraseña debe ser diferente a la actual");
        }

        usuario.setPassword(passwordEncoder.encode(contrasenaNueva));
        usuariosRepository.save(usuario);
    }

    @Override
    @Transactional
    public void agregarRol(Long idUsuario, Long idRol) {
        Usuarios usuario = buscarPorId(idUsuario);
        Roles rol = rolesRepository.findById(idRol)
                .orElseThrow(() -> new RecursoNoEncontradoException("Rol no encontrado con ID: " + idRol));

        usuario.agregarRol(rol);
        usuariosRepository.save(usuario);
    }

    @Override
    @Transactional
    public void removerRol(Long idUsuario, Long idRol) {
        Usuarios usuario = buscarPorId(idUsuario);
        Roles rol = rolesRepository.findById(idRol)
                .orElseThrow(() -> new RecursoNoEncontradoException("Rol no encontrado con ID: " + idRol));

        usuario.removerRol(rol);
        usuariosRepository.save(usuario);
    }

    @Override
    public boolean existeEmail(String email) {
        return usuariosRepository.existsByEmail(email);
    }

    @Override
    public boolean existeUsername(String username) {
        return usuariosRepository.existsByUsername(username);
    }

    @Override
    public long contarUsuariosActivos() {
        return usuariosRepository.contarUsuariosActivos();
    }

    @Override
    public List<Usuarios> buscarPorRol(String nombreRol) {
        return usuariosRepository.buscarPorRol(nombreRol);
    }

    private void validarUsuarioNuevo(Usuarios usuario) {
        if (usuario.getName() == null || usuario.getName().trim().isEmpty()) {
            throw new ValidacionException("El nombre es requerido");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new ValidacionException("El email es requerido");
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new ValidacionException("La contraseña es requerida");
        }
    }
}
