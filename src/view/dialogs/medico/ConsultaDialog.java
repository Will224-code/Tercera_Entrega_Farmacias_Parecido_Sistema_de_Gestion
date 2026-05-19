package view.dialogs.medico;

import controller.ConsultaController;
import model.entity.Cita;
import model.entity.Usuario;
import model.repository.AuditoriaRepository;
import model.repository.ConsultaRepository;
import model.service.AuditoriaService;
import model.service.ConsultaService;
import view.panels.medico.MedicoConsultaPanel;

import javax.swing.*;
import java.awt.*;

public class ConsultaDialog extends JDialog {
    private final ConsultaController consultaController;

    public ConsultaDialog(Frame parent, Cita cita, Usuario usuarioActual) {
        super(parent, "Registrar Consulta - Cita ID: " + cita.getIdCita(), true);
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Crear panel de consulta
        MedicoConsultaPanel panel = new MedicoConsultaPanel();

        // Servicios necesarios
        ConsultaRepository consultaRepo = new ConsultaRepository();
        AuditoriaRepository auditoriaRepo = new AuditoriaRepository();
        AuditoriaService auditoriaService = new AuditoriaService(auditoriaRepo);
        ConsultaService consultaService = new ConsultaService(consultaRepo, auditoriaService);

        // Controlador
        consultaController = new ConsultaController(panel, consultaService, usuarioActual, cita);

        add(panel, BorderLayout.CENTER);
    }
}