package controller;

import model.entity.Cita;
import model.entity.Usuario;
import model.service.CitaService;
import view.panels.medico.MedicoAgendaPanel;
import view.dialogs.medico.ConsultaDialog;

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
                JOptionPane.showMessageDialog(vista,
                        "Detalle de cita ID: " + idCita,
                        "Ver detalle",
                        JOptionPane.INFORMATION_MESSAGE);
                // TODO: mostrar diálogo con todos los datos de la cita
            });

            vista.setBtnIniciarConsultaListener(fila, _ -> {
                int idCita = (int) vista.getTableModel().getValueAt(fila, 0);
                // Obtener la cita completa desde el servicio
                Cita cita = citaService.buscarPorId(idCita);
                if (cita != null) {
                    SwingUtilities.invokeLater(() -> {
                        Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
                        ConsultaDialog dialog = new ConsultaDialog(parent, cita, usuarioActual);
                        dialog.setVisible(true);
                        // Al cerrar el diálogo, recargar la agenda por si cambió el estado de la cita
                        cargarCitas();
                    });
                } else {
                    JOptionPane.showMessageDialog(vista,
                            "No se pudo encontrar la cita ID: " + idCita,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}