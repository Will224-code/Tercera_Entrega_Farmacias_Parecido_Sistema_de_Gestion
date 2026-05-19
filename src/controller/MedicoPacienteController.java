package controller;

import model.entity.Paciente;
import model.entity.Usuario;
import model.service.PacienteService;
import view.panels.medico.MedicoPacientePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MedicoPacienteController {
    private final MedicoPacientePanel vista;
    private final PacienteService pacienteService;
    private final Usuario usuarioActual;

    public MedicoPacienteController(MedicoPacientePanel vista, PacienteService pacienteService, Usuario usuarioActual) {
        this.vista = vista;
        this.pacienteService = pacienteService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
    }

    private void iniciarListeners() {
        vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = vista.getTxtBusqueda().getText().trim();
                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Ingrese un nombre o expediente", "Búsqueda", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                List<Paciente> lista = pacienteService.buscarPorNombre(texto);
                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "No se encontraron pacientes", "Resultado", JOptionPane.INFORMATION_MESSAGE);
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
            }
        });
    }

    private void configurarListenersPorFila(int totalFilas) {
        for (int i = 0; i < totalFilas; i++) {
            final int fila = i;
            vista.setBtnVerExpedienteListener(fila, e -> {
                String expediente = (String) vista.getTableModel().getValueAt(fila, 0);
                JOptionPane.showMessageDialog(vista,
                        "Ver expediente de paciente: " + expediente,
                        "Expediente",
                        JOptionPane.INFORMATION_MESSAGE);
                // TODO: abrir diálogo con el historial clínico completo
            });
        }
    }
}