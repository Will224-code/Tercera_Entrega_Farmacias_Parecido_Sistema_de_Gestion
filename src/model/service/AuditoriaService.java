package model.service;

import model.entity.Auditoria;
import model.entity.Usuario;
import model.repository.AuditoriaRepository;
import java.sql.Timestamp;
import java.util.List;

/**
 * Service para gestionar registros de auditoría.
 * Conecta con AuditoriaObserver mediante patrón Observer.
 */
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    /**
     * Registra una acción en la auditoría.
     * @param usuario Usuario que realiza la acción
     * @param entidad Nombre de la entidad afectada (ej. "Paciente")
     * @param idEntidad Identificador del registro afectado
     * @param accion Descripción de la acción (ej. "CREAR", "ACTUALIZAR", "DESHABILITAR")
     */
    public void registrar(Usuario usuario, String entidad, int idEntidad, String accion) {
        if (usuario == null) {
            System.err.println("Advertencia: No se puede registrar auditoría sin usuario");
            return;
        }
        Auditoria a = new Auditoria(
            0,
            usuario,
            entidad,
            idEntidad,
            accion,
            new Timestamp(System.currentTimeMillis())
        );
        auditoriaRepository.registrar(a);
    }

    public List<Auditoria> obtenerTodos() {
        return auditoriaRepository.buscarTodos();
    }
}
