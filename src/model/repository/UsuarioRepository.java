package model.repository;

import model.entity.Usuario;
import model.enums.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository simulado para Usuario usando ArrayList en memoria.
 * No requiere conexión a base de datos.
 */
public class UsuarioRepository {

    private static final List<Usuario> usuarios = new ArrayList<>();
    private static int nextId = 1;

    static {
        // Datos iniciales
        Usuario admin = new Usuario(nextId++, "admin", "admin", Rol.ADMINISTRADOR, true);
        Usuario medico = new Usuario(nextId++, "medico", "medico", Rol.MEDICO, true);
        usuarios.add(admin);
        usuarios.add(medico);
    }

    public Usuario buscarPorNombre(String nombreUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario) && u.isActivo()) {
                return clonar(u);
            }
        }
        return null;
    }

    public Usuario buscarPorId(int idUsuario) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == idUsuario) {
                return clonar(u);
            }
        }
        return null;
    }

    public List<Usuario> buscarTodos() {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : usuarios) {
            resultado.add(clonar(u));
        }
        return resultado;
    }

    public void actualizarUltimaActividad(int idUsuario) {
        // Simulado - no hay persistencia real
    }

    private Usuario clonar(Usuario u) {
        return new Usuario(u.getIdUsuario(), u.getNombreUsuario(), u.getContrasena(), u.getRol(), u.isActivo());
    }
}
