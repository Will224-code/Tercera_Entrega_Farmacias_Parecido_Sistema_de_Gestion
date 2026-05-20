package controller;

import model.entity.Cita;
import model.entity.Usuario;
import model.service.CitaService;
import view.panels.medico.MedicoAgendaPanel;
import view.dialogs.medico.ConsultaDialog;
import view.Components.NotificationToast;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MedicoAgendaController {
    private final MedicoAgendaPanel vista;
    private final CitaService citaService;
    private final Usuario usuarioActual;

    public MedicoAgendaController(MedicoAgendaPanel vista, CitaService citaService, Usuario usuarioActual) {
        this.vista = vista;
        this.citaService = citaService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
        cargarCitas();
    }

    private void iniciarListeners() {
        // No hay botones adicionales
    }

    private void cargarCitas() {
        // Ejecutar automatismos antes de cargar
        citaService.ejecutarAutomatismos();

        List<Cita> citas = citaService.buscarPorMedico(usuarioActual.getIdUsuario());
        if (citas == null || citas.isEmpty()) {
            vista.setDatos(null);
            return;
        }
        Object[][] datos = new Object[citas.size()][5];
        for (int i = 0; i < citas.size(); i++) {
            Cita c = citas.get(i);
            datos[i] = new Object[]{c.getIdCita(),
                    c.getPaciente().getNombreCompleto(),
                    c.getFecha(),
                    c.getHora(),
                    c.getEstadoCita().getNombre()};
        }
        vista.setDatos(datos);
        configurarListenersPorFila(citas.size());
    }

    private void configurarListenersPorFila(int totalFilas) {
        for (int i = 0; i < totalFilas; i++) {
            final int fila = i;
            vista.setBtnVerDetalleListener(fila, _ -> {
                int idCita = (int) vista.getTableModel().getValueAt(fila, 0);
                Cita cita = citaService.buscarPorId(idCita);
                if (cita != null) {
                    JOptionPane.showMessageDialog(vista,
                        "Paciente: " + cita.getPaciente().getNombreCompleto() + " " +
                        "Fecha: " + cita.getFecha() + " " + cita.getHora() + " " +
                        "Estado: " + cita.getEstadoCita().getNombre() + " " +
                        "Consultorio: " + (cita.getConsultorio() != null ? cita.getConsultorio() : "No asignado"),
                        "Detalle de Cita ID: " + idCita,
                        JOptionPane.INFORMATION_MESSAGE);
                }
            });

            vista.setBtnIniciarConsultaListener(fila, _ -> {
                int idCita = (int) vista.getTableModel().getValueAt(fila, 0);
                Cita cita = citaService.buscarPorId(idCita);
                if (cita != null) {
                    // Solo permitir iniciar consulta si estado es CONFIRMADA
                    String estado = cita.getEstadoCita().getNombre().toUpperCase().replace(" ", "_");
                    if (!estado.equals("CONFIRMADA")) {
                        NotificationToast.show(vista, "Solo se puede iniciar consulta en citas CONFIRMADAS", "error");
                        return;
                    }

                    SwingUtilities.invokeLater(() -> {
                        Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
                        ConsultaDialog dialog = new ConsultaDialog(parent, cita, usuarioActual);
                        dialog.setVisible(true);
                        // Al cerrar, recargar la agenda
                        cargarCitas();
                    });
                } else {
                    NotificationToast.show(vista, "No se pudo encontrar la cita", "error");
                }
            });
        }
    }
}
