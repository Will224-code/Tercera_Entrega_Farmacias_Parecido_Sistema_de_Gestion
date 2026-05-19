package view.frames;

import controller.MedicoAgendaController;
import controller.MedicoPacienteController;
import model.entity.Usuario;
import model.repository.*;
import model.service.*;
import view.panels.medico.MedicoAgendaPanel;
import view.panels.medico.MedicoConsultaPanel;
import view.panels.medico.MedicoPacientePanel;

import javax.swing.*;

public class MedicoDashboardFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private MedicoAgendaPanel agendaPanel;
    private MedicoPacientePanel pacientePanel;
    private MedicoConsultaPanel consultaPanel;
    private JMenuItem itemLogout;

    public MedicoDashboardFrame(Usuario usuarioActual) {
        setTitle("SGCM - Panel del Médico");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agendaPanel = new MedicoAgendaPanel();
        pacientePanel = new MedicoPacientePanel();
        consultaPanel = new MedicoConsultaPanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Mi Agenda", agendaPanel);
        tabbedPane.addTab("Pacientes", pacientePanel);
        tabbedPane.addTab("Consulta", consultaPanel);
        add(tabbedPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        itemLogout = new JMenuItem("Cerrar Sesión");
        itemLogout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        menuArchivo.add(itemLogout);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        // ---------- Crear servicios y controladores ----------
        AuditoriaRepository auditoriaRepo = new AuditoriaRepository();
        AuditoriaService auditoriaService = new AuditoriaService(auditoriaRepo);

        CitaRepository citaRepo = new CitaRepository();
        CitaService citaService = new CitaService(citaRepo, auditoriaService);
        new MedicoAgendaController(agendaPanel, citaService, usuarioActual);

        PacienteRepository pacienteRepo = new PacienteRepository();
        PacienteService pacienteService = new PacienteService(pacienteRepo, auditoriaService);
        new MedicoPacienteController(pacientePanel, pacienteService, usuarioActual);

        // El controlador de Consulta se crea al iniciar una consulta (dentro de ConsultaDialog)
    }

    // Getters públicos
    public MedicoAgendaPanel getAgendaPanel() { return agendaPanel; }
    public MedicoPacientePanel getPacientePanel() { return pacientePanel; }
    public MedicoConsultaPanel getConsultaPanel() { return consultaPanel; }
    public JMenuItem getBtnLogout() { return itemLogout; }
}