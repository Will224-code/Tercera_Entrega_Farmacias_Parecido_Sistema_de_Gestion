package controller;

import model.entity.Medico;
import model.entity.Usuario;
import model.service.MedicoService;
import view.panels.admin.AdminMedicoPanel;

import javax.swing.*;
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
            JOptionPane.showMessageDialog(vista,
                    "Abrir diálogo de registro de médico",
                    "Registrar",
                    JOptionPane.INFORMATION_MESSAGE);
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
                medicoService.deshabilitar(id, usuarioActual);
                JOptionPane.showMessageDialog(vista,
                        "Médico deshabilitado. Recargando...",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarTodos();
            });
        }
    }
}