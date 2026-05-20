package controller;

import model.entity.Auditoria;
import model.entity.Usuario;
import model.service.AuditoriaService;
import view.panels.admin.AdminAuditoriaPanel;

import javax.swing.*;
import java.util.List;

public class AuditoriaController {
    private final AdminAuditoriaPanel vista;
    private final AuditoriaService auditoriaService;

    public AuditoriaController(AdminAuditoriaPanel vista, AuditoriaService auditoriaService) {
        this.vista = vista;
        this.auditoriaService = auditoriaService;
        iniciarListeners();
        cargarAuditoria();
    }

    private void iniciarListeners() {
        vista.getBtnRefrescar().addActionListener(e -> cargarAuditoria());
    }

    private void cargarAuditoria() {
        List<Auditoria> registros = auditoriaService.obtenerTodos();
        if (registros == null || registros.isEmpty()) {
            vista.setDatos(null);
            return;
        }
        Object[][] datos = new Object[registros.size()][6];
        for (int i = 0; i < registros.size(); i++) {
            Auditoria a = registros.get(i);
            datos[i] = new Object[]{
                a.getIdAuditoria(),
                a.getUsuario().getNombreUsuario(),
                a.getEntidadAuditada(),
                a.getIdEntidad(),
                a.getAccion(),
                a.getFechaHora()
            };
        }
        vista.setDatos(datos);
    }
}
