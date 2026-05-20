package controller;

import model.entity.Cita;
import model.entity.Paciente;
import model.entity.Medico;
import model.entity.Usuario;
import model.factories.EstadoCitaFactory;
import model.service.CitaService;
import model.service.PacienteService;
import model.service.MedicoService;
import model.state.EstadoCita;
import view.panels.admin.AdminCitaPanel;
import view.dialogs.cita.CitaAgendarDialog;
import view.dialogs.cita.CitaCancelarDialog;
import view.Components.NotificationToast;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CitaController {
    private final AdminCitaPanel vista;
    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final Usuario usuarioActual;

    public CitaController(AdminCitaPanel vista, CitaService citaService,
                          PacienteService pacienteService, MedicoService medicoService,
                          Usuario usuarioActual) {
        this.vista = vista;
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
        cargarTodas();
    }

    private void iniciarListeners() {
        vista.getBtnAgendar().addActionListener(e -> {
            Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
            CitaAgendarDialog dialog = new CitaAgendarDialog(parent);

            // Cargar pacientes y médicos en combos
            List<Paciente> pacientes = pacienteService.buscarTodos();
            List<Medico> medicos = medicoService.buscarTodos();
            for (Paciente p : pacientes) {
                dialog.getCmbPaciente().addItem(p.getIdPaciente() + " - " + p.getNombreCompleto());
            }
            for (Medico m : medicos) {
                dialog.getCmbMedico().addItem(m.getIdMedico() + " - " + m.getNombreCompleto());
            }

            dialog.getBtnGuardar().addActionListener(ev -> {
                try {
                    String pacStr = (String) dialog.getCmbPaciente().getSelectedItem();
                    String medStr = (String) dialog.getCmbMedico().getSelectedItem();
                    int idPaciente = Integer.parseInt(pacStr.split(" - ")[0]);
                    int idMedico = Integer.parseInt(medStr.split(" - ")[0]);

                    Paciente paciente = pacienteService.buscarPorExpediente(
                        pacienteService.buscarTodos().stream()
                            .filter(p -> p.getIdPaciente() == idPaciente)
                            .findFirst().orElse(null).getNumeroExpediente()
                    );
                    Medico medico = medicoService.buscarPorId(idMedico);

                    Cita cita = citaService.agendar(paciente, medico,
                        dialog.getFechaSeleccionada(), dialog.getHoraSeleccionada(), usuarioActual);

                    if (cita != null) {
                        NotificationToast.show(dialog, "Cita agendada exitosamente", "info");
                        dialog.dispose();
                        cargarTodas();
                    } else {
                        NotificationToast.show(dialog, "Error: Conflicto de horario o datos inválidos", "error");
                    }
                } catch (Exception ex) {
                    NotificationToast.show(dialog, "Error al agendar: " + ex.getMessage(), "error");
                }
            });
            dialog.setVisible(true);
        });
    }

    private void cargarTodas() {
        List<Cita> lista = citaService.buscarTodas();
        if (lista == null || lista.isEmpty()) {
            vista.setDatos(null);
            return;
        }
        Object[][] datos = new Object[lista.size()][6];
        for (int i = 0; i < lista.size(); i++) {
            Cita c = lista.get(i);
            datos[i] = new Object[]{c.getIdCita(),
                    c.getPaciente().getNombreCompleto(),
                    c.getMedico().getNombreCompleto(),
                    c.getFecha(),
                    c.getHora(),
                    c.getEstadoCita().getNombre()};
        }
        vista.setDatos(datos);
        configurarListenersPorFila(lista.size());
    }

    private void configurarListenersPorFila(int totalFilas) {
        for (int i = 0; i < totalFilas; i++) {
            final int fila = i;
            vista.setBtnCambiarEstadoListener(fila, e -> {
                int idCita = (int) vista.getTableModel().getValueAt(fila, 0);
                String[] opciones = {"CONFIRMADA", "COMPLETADA", "CANCELADA", "NO_PRESENTADA", "EXPIRADA"};
                String seleccion = (String) JOptionPane.showInputDialog(vista,
                        "Seleccione el nuevo estado",
                        "Cambiar estado de cita",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);
                if (seleccion != null) {
                    EstadoCita nuevo = EstadoCitaFactory.crearEstado(seleccion);
                    citaService.cambiarEstado(idCita, nuevo, usuarioActual);
                    NotificationToast.show(vista, "Estado actualizado", "info");
                    cargarTodas();
                }
            });
            vista.setBtnCancelarListener(fila, e -> {
                int idCita = (int) vista.getTableModel().getValueAt(fila, 0);
                Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
                CitaCancelarDialog dialog = new CitaCancelarDialog(parent, idCita);
                dialog.getBtnConfirmar().addActionListener(ev -> {
                    String motivo = dialog.getTxtMotivo().getText().trim();
                    if (motivo.isEmpty()) {
                        NotificationToast.show(dialog, "El motivo es obligatorio", "error");
                        return;
                    }
                    citaService.cancelar(idCita, motivo, usuarioActual);
                    NotificationToast.show(dialog, "Cita cancelada", "info");
                    dialog.dispose();
                    cargarTodas();
                });
                dialog.setVisible(true);
            });
        }
    }
}
