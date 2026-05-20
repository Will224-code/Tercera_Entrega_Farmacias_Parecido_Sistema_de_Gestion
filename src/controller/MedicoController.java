package controller;

import model.entity.Medico;
import model.entity.Usuario;
import model.service.MedicoService;
import view.panels.admin.AdminMedicoPanel;
import view.dialogs.medico.MedicoRegistroDialog;
import view.Components.NotificationToast;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MedicoController {
    private final AdminMedicoPanel vista;
    private final MedicoService medicoService;
    private final Usuario usuarioActual;

    public MedicoController(AdminMedicoPanel vista, MedicoService medicoService, Usuario usuarioActual) {
        this.vista = vista;
        this.medicoService = medicoService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
        cargarTodos();
    }

    private void iniciarListeners() {
        vista.getBtnRegistrar().addActionListener(_ -> {
            Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
            MedicoRegistroDialog dialog = new MedicoRegistroDialog(parent);
            dialog.getBtnGuardar().addActionListener(ev -> {
                String nombre = dialog.getTxtNombre().getText().trim();
                var especialidad = (model.enums.Especialidad) dialog.getCmbEspecialidad().getSelectedItem();
                String telFijo = dialog.getTxtTelFijo().getText().trim();
                String telCel = dialog.getTxtTelCelular().getText().trim();
                String correo = dialog.getTxtCorreo().getText().trim();

                if (nombre.isEmpty()) {
                    NotificationToast.show(dialog, "El nombre es obligatorio", "error");
                    return;
                }

                Medico m = medicoService.registrar(nombre, especialidad, telFijo, telCel, correo, usuarioActual);
                if (m != null) {
                    NotificationToast.show(dialog, "Médico registrado exitosamente", "info");
                    dialog.dispose();
                    cargarTodos();
                } else {
                    NotificationToast.show(dialog, "Error al registrar médico", "error");
                }
            });
            dialog.setVisible(true);
        });
    }

    private void cargarTodos() {
        List<Medico> lista = medicoService.buscarTodos();
        if (lista == null || lista.isEmpty()) {
            vista.setDatos(null);
            return;
        }
        Object[][] datos = new Object[lista.size()][5];
        for (int i = 0; i < lista.size(); i++) {
            Medico m = lista.get(i);
            datos[i] = new Object[]{m.getIdMedico(), m.getNombreCompleto(),
                    m.getEspecialidad().toString(), m.getTelefonoFijo(),
                    m.isActivo() ? "Activo" : "Inactivo"};
        }
        vista.setDatos(datos);
        configurarListenersPorFila(lista.size());
    }

    private void configurarListenersPorFila(int totalFilas) {
        for (int i = 0; i < totalFilas; i++) {
            final int fila = i;
            vista.setBtnDeshabilitarListener(fila, _ -> {
                int id = (int) vista.getTableModel().getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(vista,
                        "¿Deshabilitar este médico? Se cancelarán todas sus citas futuras.",
                        "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    medicoService.deshabilitar(id, usuarioActual);
                    NotificationToast.show(vista, "Médico deshabilitado. Recargando...", "info");
                    cargarTodos();
                }
            });
        }
    }
}
