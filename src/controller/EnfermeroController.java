package controller;

import model.entity.Enfermero;
import model.entity.Usuario;
import model.service.EnfermeroService;
import view.panels.admin.AdminEnfermeroPanel;
import view.Components.NotificationToast;

import javax.swing.*;
import java.util.List;

public class EnfermeroController {
    private final AdminEnfermeroPanel vista;
    private final EnfermeroService enfermeroService;
    private final Usuario usuarioActual;

    public EnfermeroController(AdminEnfermeroPanel vista, EnfermeroService enfermeroService, Usuario usuarioActual) {
        this.vista = vista;
        this.enfermeroService = enfermeroService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
        cargarTodos();
    }

    private void iniciarListeners() {
        vista.getBtnRegistrar().addActionListener(e -> {
            NotificationToast.show(vista, "Diálogo de registro de enfermero (por implementar)", "info");
        });
    }

    private void cargarTodos() {
        List<Enfermero> lista = enfermeroService.buscarTodos();
        if (lista == null || lista.isEmpty()) {
            vista.setDatos(null);
            return;
        }
        Object[][] datos = new Object[lista.size()][5];
        for (int i = 0; i < lista.size(); i++) {
            Enfermero e = lista.get(i);
            datos[i] = new Object[]{
                e.getIdEnfermero(),
                e.getNombreCompleto(),
                e.getCurp(),
                e.getMedico() != null ? e.getMedico().getNombreCompleto() : "Sin asignar",
                e.isActivo() ? "Activo" : "Inactivo"
            };
        }
        vista.setDatos(datos);
        configurarListenersPorFila(lista.size());
    }

    private void configurarListenersPorFila(int totalFilas) {
        for (int i = 0; i < totalFilas; i++) {
            final int fila = i;
            vista.setBtnDeshabilitarListener(fila, e -> {
                int id = (int) vista.getTableModel().getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(vista,
                    "¿Deshabilitar este enfermero?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    enfermeroService.deshabilitar(id, usuarioActual);
                    NotificationToast.show(vista, "Enfermero deshabilitado", "info");
                    cargarTodos();
                }
            });
        }
    }
}
