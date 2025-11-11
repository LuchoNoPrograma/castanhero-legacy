package uap.usic.siga.service.compartido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uap.usic.siga.domain.compartido.Roles;
import uap.usic.siga.domain.compartido.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuariosService {

    Usuarios buscarPorId(Long id);

    Usuarios buscarPorEmail(String email);

    Usuarios buscarPorUsername(String username);

    Usuarios buscarPorName(String name);

    Optional<Usuarios> buscarPorIdPersona(Long idPersona);

    List<Usuarios> listarUsuariosActivos();

    Page<Usuarios> listarPaginado(Pageable pageable);

    Usuarios registrar(Usuarios usuario);

    Usuarios actualizar(Long id, Usuarios usuario);

    void eliminar(Long id);

    void activar(Long id);

    void desactivar(Long id);

    void cambiarContrasena(Long id, String contrasenaActual, String contrasenaNueva);

    void agregarRol(Long idUsuario, Long idRol);

    void removerRol(Long idUsuario, Long idRol);

    boolean existeEmail(String email);

    boolean existeUsername(String username);

    long contarUsuariosActivos();

    List<Usuarios> buscarPorRol(String nombreRol);
}
