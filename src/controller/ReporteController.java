package controller;

import model.entity.ReporteDiario;
import model.entity.Usuario;
import model.service.ReporteService;
import view.panels.admin.AdminReportePanel;
import view.Components.NotificationToast;

import javax.swing.*;
import java.util.List;

public class ReporteController {
    private final AdminReportePanel vista;
    private final ReporteService reporteService;
    private final Usuario usuarioActual;

    public ReporteController(AdminReportePanel vista, ReporteService reporteService, Usuario usuarioActual) {
        this.vista = vista;
        this.reporteService = reporteService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
    }

    private void iniciarListeners() {
        vista.getBtnGenerar().addActionListener(e -> {
            List<ReporteDiario> reportes = reporteService.generarPorFiltros(
                vista.getFechaInicio(),
                vista.getFechaFin(),
                vista.getTxtMedico().getText().trim(),
                (String) vista.getCmbEspecialidad().getSelectedItem(),
                vista.getTxtPaciente().getText().trim()
            );

            if (reportes == null || reportes.isEmpty()) {
                NotificationToast.show(vista, "No se encontraron resultados", "info");
                vista.setDatos(null);
                return;
            }

            Object[][] datos = new Object[reportes.size()][6];
            for (int i = 0; i < reportes.size(); i++) {
                ReporteDiario r = reportes.get(i);
                datos[i] = new Object[]{
                    r.getFecha(), r.getConsultorio(), r.getEspecialidad(),
                    r.getNombreMedico(), r.getNombrePaciente(), r.getMotivoConsulta()
                };
            }
            vista.setDatos(datos);
            NotificationToast.show(vista, reportes.size() + " registros encontrados", "info");
        });

        vista.getBtnLimpiar().addActionListener(e -> {
            vista.getTxtFechaInicio().setText("");
            vista.getTxtFechaFin().setText("");
            vista.getTxtMedico().setText("");
            vista.getCmbEspecialidad().setSelectedIndex(0);
            vista.getTxtPaciente().setText("");
            vista.setDatos(null);
        });
    }
}
