package controller;

import model.entity.Paciente;
import model.entity.Usuario;
import model.service.PacienteService;
import view.panels.admin.AdminPacientePanel;

import javax.swing.*;
import java.util.List;

public class PacienteController {
    private final AdminPacientePanel vista;
    private final PacienteService pacienteService;
    private final Usuario usuarioActual;

    public PacienteController(AdminPacientePanel vista, PacienteService pacienteService, Usuario usuarioActual) {
        this.vista = vista;
        this.pacienteService = pacienteService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
        cargarTodos();
    }

    private void iniciarListeners() {
        vista.getBtnBuscar().addActionListener(e -> {
            String texto = vista.getTxtBusqueda().getText().trim();
            if (texto.isEmpty()) {
                cargarTodos();
            } else {
                Paciente p = pacienteService.buscarPorExpediente(texto);
                if (p == null) {
                    JOptionPane.showMessageDialog(vista,
                            "No se encontraron pacientes con ese expediente",
                            "Búsqueda",
                            JOptionPane.INFORMATION_MESSAGE);
                    vista.setDatos(null);
                } else {
                    Object[][] datos = new Object[1][6];
                    datos[0] = new Object[]{p.getIdPaciente(), p.getNumeroExpediente(),
                            p.getNombreCompleto(), p.getCurp(), p.getEdad(),
                            p.isActivo() ? "Activo" : "Inactivo"};
                    vista.setDatos(datos);
                    configurarListenersPorFila(1);
                }
            }
        });

        vista.getBtnRegistrar().addActionListener(e -> {
            JOptionPane.showMessageDialog(vista,
                    "Abrir diálogo de registro de paciente",
                    "Registrar",
                    JOptionPane.INFORMATION_MESSAGE);
            // TODO: abrir PacienteRegistroDialog
        });
    }

    private void cargarTodos() {
        List<Paciente> lista = pacienteService.buscarTodos();
        if (lista == null || lista.isEmpty()) {
            vista.setDatos(null);
            return;
        }
        Object[][] datos = new Object[lista.size()][6];
        for (int i = 0; i < lista.size(); i++) {
            Paciente p = lista.get(i);
            datos[i] = new Object[]{p.getIdPaciente(), p.getNumeroExpediente(),
                    p.getNombreCompleto(), p.getCurp(), p.getEdad(),
                    p.isActivo() ? "Activo" : "Inactivo"};
        }
        vista.setDatos(datos);
        configurarListenersPorFila(lista.size());
    }

    private void configurarListenersPorFila(int totalFilas) {
        for (int i = 0; i < totalFilas; i++) {
            final int fila = i;
            vista.setBtnEditarListener(fila, _ -> {
                int idPaciente = (int) vista.getTableModel().getValueAt(fila, 0);
                JOptionPane.showMessageDialog(vista,
                        "Editar paciente con ID: " + idPaciente,
                        "Editar",
                        JOptionPane.INFORMATION_MESSAGE);
                // TODO: abrir diálogo de edición
            });
            vista.setBtnDeshabilitarListener(fila, _ -> {
                int idPaciente = (int) vista.getTableModel().getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(vista,
                        "¿Deshabilitar este paciente?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    pacienteService.deshabilitar(idPaciente, usuarioActual);
                    JOptionPane.showMessageDialog(vista, "Paciente deshabilitado");
                    cargarTodos();
                }
            });
        }
    }
}