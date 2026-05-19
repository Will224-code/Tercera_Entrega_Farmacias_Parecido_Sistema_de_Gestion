package view.frames;

import controller.CitaController;
import controller.MedicoController;
import controller.PacienteController;
import model.entity.Usuario;
import model.repository.*;
import model.service.*;
import view.panels.admin.*;
import model.repository.*;
import model.service.*;
import controller.*;
import javax.swing.*;

public class AdminDashboardFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private AdminPacientePanel pacientePanel;
    private AdminMedicoPanel medicoPanel;
    private AdminCitaPanel citaPanel;
    private AdminEnfermeroPanel enfermeroPanel;
    private AdminReportePanel reportePanel;
    private AdminAuditoriaPanel auditoriaPanel;
    private JMenuItem itemLogout;

    // Constructor modificado para recibir el usuario actual
    public AdminDashboardFrame(Usuario usuarioActual) {
        setTitle("SGCM - Panel Administrador");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear paneles
        pacientePanel = new AdminPacientePanel();
        medicoPanel = new AdminMedicoPanel();
        citaPanel = new AdminCitaPanel();
        enfermeroPanel = new AdminEnfermeroPanel();
        reportePanel = new AdminReportePanel();
        auditoriaPanel = new AdminAuditoriaPanel();

        // Configurar pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pacientes", pacientePanel);
        tabbedPane.addTab("Médicos", medicoPanel);
        tabbedPane.addTab("Citas", citaPanel);
        tabbedPane.addTab("Enfermeros", enfermeroPanel);
        tabbedPane.addTab("Reportes", reportePanel);
        tabbedPane.addTab("Auditoría", auditoriaPanel);
        add(tabbedPane);

        // Barra de menú
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

        PacienteRepository pacienteRepo = new PacienteRepository();
        PacienteService pacienteService = new PacienteService(pacienteRepo, auditoriaService);
        new PacienteController(pacientePanel, pacienteService, usuarioActual);

        MedicoRepository medicoRepo = new MedicoRepository();
        CitaRepository citaRepo = new CitaRepository();
        MedicoService medicoService = new MedicoService(medicoRepo, citaRepo, auditoriaService);
        new MedicoController(medicoPanel, medicoService, usuarioActual);

        CitaService citaService = new CitaService(citaRepo, auditoriaService);
        new CitaController(citaPanel, citaService, usuarioActual);
    }

    // -------------------- MÉTODOS PÚBLICOS PARA EL CONTROLLER --------------------
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public AdminPacientePanel getPacientePanel() {
        return pacientePanel;
    }

    public AdminMedicoPanel getMedicoPanel() {
        return medicoPanel;
    }

    public AdminCitaPanel getCitaPanel() {
        return citaPanel;
    }

    public AdminEnfermeroPanel getEnfermeroPanel() {
        return enfermeroPanel;
    }

    public AdminReportePanel getReportePanel() {
        return reportePanel;
    }

    public AdminAuditoriaPanel getAuditoriaPanel() {
        return auditoriaPanel;
    }

    public JMenuItem getBtnLogout() {
        return itemLogout;
    }
}