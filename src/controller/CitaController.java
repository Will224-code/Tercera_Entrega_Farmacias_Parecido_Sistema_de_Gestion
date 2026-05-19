package controller;

import model.entity.Cita;
import model.entity.Usuario;
import model.factories.EstadoCitaFactory;
import model.service.CitaService;
import view.panels.admin.AdminCitaPanel;
import model.state.EstadoCita;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CitaController {
    private final AdminCitaPanel vista;
    private final CitaService citaService;
    private final Usuario usuarioActual;

    public CitaController(AdminCitaPanel vista, CitaService citaService, Usuario usuarioActual) {
        this.vista = vista;
        this.citaService = citaService;
        this.usuarioActual = usuarioActual;
        iniciarListeners();
        cargarTodas();
    }

    private void iniciarListeners() {
        vista.getBtnAgendar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vista,
                        "Abrir diálogo de agendar cita",
                        "Agendar",
                        JOptionPane.INFORMATION_MESSAGE);
                // TODO: abrir diálogo de cita
            }
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
                    JOptionPane.showMessageDialog(vista, "Estado actualizado");
                    cargarTodas();
                }
            });
            vista.setBtnCancelarListener(fila, e -> {
                int idCita = (int) vista.getTableModel().getValueAt(fila, 0);
                String motivo = JOptionPane.showInputDialog(vista, "Motivo de cancelación:");
                if (motivo != null && !motivo.trim().isEmpty()) {
                    citaService.cancelar(idCita, motivo, usuarioActual);
                    JOptionPane.showMessageDialog(vista, "Cita cancelada");
                    cargarTodas();
                }
            });
        }
    }
}