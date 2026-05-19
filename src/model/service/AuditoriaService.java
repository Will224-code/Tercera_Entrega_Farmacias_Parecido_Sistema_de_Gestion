package model.service;

import model.entity.Auditoria;
import model.entity.Usuario;
import model.repository.AuditoriaRepository;
import java.sql.Timestamp;

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
        // TODO: Construir objeto Auditoria con fechaHora actual y llamar a auditoriaRepository.registrar(a)
        // Auditoria a = new Auditoria(0, usuario, entidad, idEntidad, accion, new Timestamp(System.currentTimeMillis()));
        // auditoriaRepository.registrar(a);
    }
}