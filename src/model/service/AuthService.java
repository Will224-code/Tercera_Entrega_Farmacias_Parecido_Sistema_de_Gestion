package model.service;

import model.entity.Usuario;
import model.enums.Rol;
import model.repository.UsuarioRepository;

public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuditoriaService auditoriaService;
    private static final int TIEMPO_INACTIVIDAD_MINUTOS = 15; // configurable

    public AuthService(UsuarioRepository usuarioRepository, AuditoriaService auditoriaService) {
        this.usuarioRepository = usuarioRepository;
        this.auditoriaService = auditoriaService;
    }

    /**
     * Valida credenciales y retorna el usuario si es válido y está activo.
     * @param nombreUsuario Nombre de usuario
     * @param contrasena Contraseña (en texto plano, se asume que después se encriptará)
     * @return Usuario si es válido, null en caso contrario
     */
    public Usuario login(String nombreUsuario, String contrasena) {
        if ("admin".equals(nombreUsuario) && "admin".equals(contrasena)) {
            Usuario admin = new Usuario();
            admin.setIdUsuario(1);
            admin.setNombreUsuario("admin");
            admin.setRol(Rol.ADMINISTRADOR);
            admin.setActivo(true);
            // Registrar auditoría (opcional, pero se puede llamar)
            // auditoriaService.registrar(admin, "Login", 0, "INICIO_SESION");
            return admin;
        }
        if ("medico".equals(nombreUsuario) && "medico".equals(contrasena)) {
            Usuario medico = new Usuario();
            medico.setIdUsuario(2);
            medico.setNombreUsuario("medico");
            medico.setRol(Rol.MEDICO);
            medico.setActivo(true);
            return medico;
        }
        // Fin del código temporal

        // TODO: Buscar usuario por nombreUsuario.
        // Validar que exista, que esté activo y que la contraseña coincida.
        // Si es exitoso, registrar en auditoría "INICIO_SESION".
        // Retornar el usuario o null.
        return null;
    }

    /**
     * Cierra la sesión del usuario actual.
     * @param usuario Usuario que cierra sesión
     */
    public void logout(Usuario usuario) {
        // TODO: Registrar en auditoría "CIERRE_SESION".
        // Opcionalmente limpiar cualquier sesión activa.
    }

    /**
     * Verifica si la sesión ha expirado por inactividad.
     * @return true si ha expirado, false en caso contrario
     */
    public boolean verificarInactividad() {
        // TODO: Implementar lógica de última actividad vs tiempo actual.
        // Por ahora retorna false.
        return false;
    }
}