package controller;

import model.entity.Paciente;
import model.entity.Usuario;
import model.service.PacienteService;
import view.panels.admin.AdminPacientePanel;
import view.dialogs.paciente.PacienteRegistroDialog;
import view.dialogs.paciente.PacienteDetalleDialog;
import view.Components.NotificationToast;

import javax.swing.*;
import java.awt.*;
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
                    List<Paciente> lista = pacienteService.buscarPorNombre(texto);
                    if (lista == null || lista.isEmpty()) {
                        NotificationToast.show(vista, "No se encontraron pacientes", "info");
                        vista.setDatos(null);
                    } else {
                        cargarLista(lista);
                    }
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
            Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
            PacienteRegistroDialog dialog = new PacienteRegistroDialog(parent);
            dialog.getBtnGuardar().addActionListener(ev -> {
                try {
                    String nombre = dialog.getTxtNombre().getText().trim();
                    String curp = dialog.getTxtCURP().getText().trim();
                    String direccion = dialog.getTxtDireccion().getText().trim();
                    String estadoCivil = (String) dialog.getCmbEstadoCivil().getSelectedItem();
                    int edad = Integer.parseInt(dialog.getTxtEdad().getText().trim());

                    if (nombre.isEmpty() || curp.isEmpty()) {
                        NotificationToast.show(dialog, "Nombre y CURP son obligatorios", "error");
                        return;
                    }

                    Paciente p = pacienteService.registrar(nombre, curp, direccion, estadoCivil, edad, usuarioActual);
                    if (p != null) {
                        NotificationToast.show(dialog, "Paciente registrado exitosamente", "info");
                        dialog.dispose();
                        cargarTodos();
                    } else {
                        NotificationToast.show(dialog, "Error al registrar paciente", "error");
                    }
                } catch (NumberFormatException ex) {
                    NotificationToast.show(dialog, "Edad debe ser un número válido", "error");
                }
            });
            dialog.setVisible(true);
        });
    }

    private void cargarTodos() {
        List<Paciente> lista = pacienteService.buscarTodos();
        cargarLista(lista);
    }

    private void cargarLista(List<Paciente> lista) {
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
                NotificationToast.show(vista, "Editar paciente ID: " + idPaciente, "info");
                // TODO: Abrir diálogo de edición
            });
            vista.setBtnDeshabilitarListener(fila, _ -> {
                int idPaciente = (int) vista.getTableModel().getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(vista,
                        "¿Deshabilitar este paciente?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    pacienteService.deshabilitar(idPaciente, usuarioActual);
                    NotificationToast.show(vista, "Paciente deshabilitado", "info");
                    cargarTodos();
                }
            });
        }
    }
}
