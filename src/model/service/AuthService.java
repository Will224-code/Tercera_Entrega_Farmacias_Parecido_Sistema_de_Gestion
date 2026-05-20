package model.service;

import model.entity.Usuario;
import model.enums.Rol;
import model.repository.UsuarioRepository;

/**
 * Service para autenticación y gestión de sesiones.
 */
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuditoriaService auditoriaService;
    private static final int TIEMPO_INACTIVIDAD_MINUTOS = 15;
    private long ultimaActividad = System.currentTimeMillis();

    public AuthService(UsuarioRepository usuarioRepository, AuditoriaService auditoriaService) {
        this.usuarioRepository = usuarioRepository;
        this.auditoriaService = auditoriaService;
    }

    /**
     * Valida credenciales contra la base de datos y retorna el usuario si es válido.
     */
    public Usuario login(String nombreUsuario, String contrasena) {
        if (nombreUsuario == null || contrasena == null || nombreUsuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
            return null;
        }

        Usuario usuario = usuarioRepository.buscarPorNombre(nombreUsuario);

        if (usuario == null) {
            return null;
        }

        if (!usuario.isActivo()) {
            System.err.println("Usuario inactivo: " + nombreUsuario);
            return null;
        }

        // En producción usar BCrypt o similar
        if (!contrasena.equals(usuario.getContrasena())) {
            return null;
        }

        // Actualizar última actividad
        usuarioRepository.actualizarUltimaActividad(usuario.getIdUsuario());
        ultimaActividad = System.currentTimeMillis();

        // Registrar auditoría
        auditoriaService.registrar(usuario, "Login", 0, "INICIO_SESION");

        return usuario;
    }

    /**
     * Cierra la sesión del usuario actual.
     */
    public void logout(Usuario usuario) {
        if (usuario != null) {
            auditoriaService.registrar(usuario, "Login", 0, "CIERRE_SESION");
        }
    }

    /**
     * Verifica si la sesión ha expirado por inactividad.
     * @return true si ha expirado, false en caso contrario
     */
    public boolean verificarInactividad() {
        long tiempoActual = System.currentTimeMillis();
        long diferenciaMinutos = (tiempoActual - ultimaActividad) / (1000 * 60);
        return diferenciaMinutos >= TIEMPO_INACTIVIDAD_MINUTOS;
    }

    /**
     * Actualiza el timestamp de última actividad.
     */
    public void actualizarActividad() {
        ultimaActividad = System.currentTimeMillis();
    }

    public int getTiempoInactividadMinutos() {
        return TIEMPO_INACTIVIDAD_MINUTOS;
    }
}
