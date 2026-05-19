package controller;

import model.entity.Cita;
import model.entity.Consulta;
import model.entity.Usuario;
import model.service.ConsultaService;
import view.panels.medico.MedicoConsultaPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaController {
    private final MedicoConsultaPanel vista;
    private final ConsultaService consultaService;
    private final Usuario usuarioActual;
    private final Cita citaActual;

    public ConsultaController(MedicoConsultaPanel vista, ConsultaService consultaService,
                              Usuario usuarioActual, Cita citaActual) {
        this.vista = vista;
        this.consultaService = consultaService;
        this.usuarioActual = usuarioActual;
        this.citaActual = citaActual;
        iniciarListeners();
    }

    private void iniciarListeners() {
        vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!consultaService.verificarAcceso(usuarioActual)) {
                    JOptionPane.showMessageDialog(vista,
                            "No tiene permisos para registrar consultas",
                            "Acceso denegado",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener datos de la vista
                float estatura;
                float peso;
                float temperatura;
                try {
                    estatura = Float.parseFloat(vista.getTxtEstatura().getText());
                    peso = Float.parseFloat(vista.getTxtPeso().getText());
                    temperatura = Float.parseFloat(vista.getTxtTemperatura().getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "Datos vitales inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String observaciones = vista.getTxtObservaciones().getText();
                String diagnostico = vista.getTxtDiagnostico().getText();
                String tratamiento = vista.getTxtTratamiento().getText();
                String estudios = vista.getTxtEstudios().getText();
                String medicamentos = vista.getTxtMedicamentos().getText();

                Consulta consulta = consultaService.registrar(citaActual, estatura, peso, temperatura,
                        observaciones, diagnostico, tratamiento, estudios, medicamentos, usuarioActual);

                if (consulta != null) {
                    JOptionPane.showMessageDialog(vista, "Consulta guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    // Limpiar campos
                    vista.getTxtEstatura().setText("");
                    vista.getTxtPeso().setText("");
                    vista.getTxtTemperatura().setText("");
                    vista.getTxtObservaciones().setText("");
                    vista.getTxtDiagnostico().setText("");
                    vista.getTxtTratamiento().setText("");
                    vista.getTxtEstudios().setText("");
                    vista.getTxtMedicamentos().setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al guardar la consulta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}