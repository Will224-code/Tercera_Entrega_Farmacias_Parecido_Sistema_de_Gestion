package controller;

import model.entity.Cita;
import model.entity.Consulta;
import model.entity.Usuario;
import model.service.ConsultaService;
import model.service.PagoService;
import model.enums.MetodoPago;
import view.panels.medico.MedicoConsultaPanel;
import view.dialogs.pago.PagoRegistroDialog;
import view.dialogs.pago.ComprobanteDialog;
import view.Components.NotificationToast;

import javax.swing.*;
import java.awt.*;

public class ConsultaController {
    private final MedicoConsultaPanel vista;
    private final ConsultaService consultaService;
    private final PagoService pagoService;
    private final Usuario usuarioActual;
    private final Cita citaActual;

    public ConsultaController(MedicoConsultaPanel vista, ConsultaService consultaService,
                              PagoService pagoService, Usuario usuarioActual, Cita citaActual) {
        this.vista = vista;
        this.consultaService = consultaService;
        this.pagoService = pagoService;
        this.usuarioActual = usuarioActual;
        this.citaActual = citaActual;
        iniciarListeners();
    }

    private void iniciarListeners() {
        vista.getBtnGuardar().addActionListener(e -> {
            if (!consultaService.verificarAcceso(usuarioActual)) {
                NotificationToast.show(vista, "No tiene permisos para registrar consultas", "error");
                return;
            }

            float estatura;
            float peso;
            float temperatura;
            try {
                estatura = Float.parseFloat(vista.getTxtEstatura().getText().trim());
                peso = Float.parseFloat(vista.getTxtPeso().getText().trim());
                temperatura = Float.parseFloat(vista.getTxtTemperatura().getText().trim());
            } catch (NumberFormatException ex) {
                NotificationToast.show(vista, "Datos vitales inválidos", "error");
                return;
            }

            String observaciones = vista.getTxtObservaciones().getText().trim();
            String diagnostico = vista.getTxtDiagnostico().getText().trim();
            String tratamiento = vista.getTxtTratamiento().getText().trim();
            String estudios = vista.getTxtEstudios().getText().trim();
            String medicamentos = vista.getTxtMedicamentos().getText().trim();

            Consulta consulta = consultaService.registrar(citaActual, estatura, peso, temperatura,
                    observaciones, diagnostico, tratamiento, estudios, medicamentos, usuarioActual);

            if (consulta != null) {
                NotificationToast.show(vista, "Consulta guardada exitosamente", "info");

                // Preguntar si desea registrar pago
                int respuesta = JOptionPane.showConfirmDialog(vista,
                    "¿Desea registrar el pago de la consulta?",
                    "Pago", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    mostrarDialogoPago(consulta);
                }

                limpiarCampos();
            } else {
                NotificationToast.show(vista, "Error al guardar la consulta", "error");
            }
        });
    }

    private void mostrarDialogoPago(Consulta consulta) {
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(vista);
        PagoRegistroDialog dialog = new PagoRegistroDialog(parent);
        dialog.getBtnProcesar().addActionListener(ev -> {
            try {
                float monto = Float.parseFloat(dialog.getTxtMonto().getText().trim());
                MetodoPago metodo = (MetodoPago) dialog.getCmbMetodoPago().getSelectedItem();

                var pago = pagoService.registrar(consulta, monto, metodo, usuarioActual);
                if (pago != null) {
                    NotificationToast.show(dialog, "Pago procesado exitosamente", "info");
                    dialog.dispose();

                    // Mostrar comprobante
                    var comprobante = pagoService.generarComprobante(pago, consulta);
                    if (comprobante != null) {
                        ComprobanteDialog compDialog = new ComprobanteDialog(parent, comprobante);
                        compDialog.setVisible(true);
                    }
                } else {
                    NotificationToast.show(dialog, "Error al procesar pago", "error");
                }
            } catch (NumberFormatException ex) {
                NotificationToast.show(dialog, "Monto inválido", "error");
            }
        });
        dialog.setVisible(true);
    }

    private void limpiarCampos() {
        vista.getTxtEstatura().setText("");
        vista.getTxtPeso().setText("");
        vista.getTxtTemperatura().setText("");
        vista.getTxtObservaciones().setText("");
        vista.getTxtDiagnostico().setText("");
        vista.getTxtTratamiento().setText("");
        vista.getTxtEstudios().setText("");
        vista.getTxtMedicamentos().setText("");
    }
}
