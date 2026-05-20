package controller;

import model.entity.Paciente;
import model.entity.Usuario;
import model.entity.Consulta;
import model.entity.Cita;
import model.service.PacienteService;
import model.service.ConsultaService;
import model.service.CitaService;
import view.panels.medico.MedicoPacientePanel;
import view.dialogs.paciente.PacienteDetalleDialog;
import view.Components.NotificationToast;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MedicoPacienteController {
    private final MedicoPacientePanel vista;
    private final PacienteService pacienteService;
    private final ConsultaService consultaService;
    private final CitaService citaService;
    private final Usuario usuarioActual;

    public MedicoPacienteController(MedicoPacientePanel vista, PacienteService pacienteService,
                                    ConsultaService consultaService, CitaService citaService,
                                    Usuario usuarioActual) {
        this.vista = vista;
        this.pacienteService = pacienteService;
        this.consultaService = consultaService;
        this.citaService = citaService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
    }

    private void iniciarListeners() {
        vista.getBtnBuscar().addActionListener(e -> {
            String texto = vista.getTxtBusqueda().getText().trim();
            if (texto.isEmpty()) {
                NotificationToast.show(vista, "Ingrese un nombre o expediente", "warning");
                return;
            }
            List<Paciente> lista = pacienteService.buscarPorNombre(texto);
            if (lista == null || lista.isEmpty()) {
                NotificationToast.show(vista, "No se encontraron pacientes", "info");
                vista.setDatos(null);
            } else {
                Object[][] datos = new Object[lista.size()][5];
                for (int i = 0; i < lista.size(); i++) {
                    Paciente p = lista.get(i);
                    datos[i] = new Object[]{p.getNumeroExpediente(), p.getNombreCompleto(),
                            p.getCurp(), p.getEdad(), p.isActivo() ? "Activo" : "Inactivo"};
                }
                vista.setDatos(datos);
                configurarListenersPorFila(lista.size());
            }
        });
    }

    private void configurarListenersPorFila(int totalFilas) {
        for (int i = 0; i < totalFilas; i++) {
            final int fila = i;
            vista.setBtnVerExpedienteListener(fila, e -> {
                String expediente = (String) vista.getTableModel().getValueAt(fila, 0);
                Paciente paciente = pacienteService.buscarPorExpediente(expediente);
                if (paciente != null) {
                    Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
                    PacienteDetalleDialog dialog = new PacienteDetalleDialog(parent, paciente);

                    // Cargar citas y consultas del paciente
                    List<Cita> citas = citaService.buscarPorPaciente(paciente.getIdPaciente());
                    List<Consulta> consultas = consultaService.buscarPorPaciente(paciente.getIdPaciente());
                    dialog.cargarCitas(citas);
                    dialog.cargarConsultas(consultas);

                    dialog.setVisible(true);
                }
            });
        }
    }
}
