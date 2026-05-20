package model.observer;

import model.entity.Usuario;
import model.service.AuditoriaService;

public class AuditoriaObserver implements Observador {
    private final AuditoriaService auditoriaService;
    private final Usuario usuarioActual;

    public AuditoriaObserver(AuditoriaService auditoriaService, Usuario usuarioActual) {
        this.auditoriaService = auditoriaService;
        this.usuarioActual = usuarioActual;
    }

    @Override
    public void actualizar(String entidad, String accion, int idEntidad) {
        auditoriaService.registrar(usuarioActual, entidad, idEntidad, accion);
    }
}