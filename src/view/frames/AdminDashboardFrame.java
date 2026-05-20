package view.frames;

import controller.*;
import model.entity.Usuario;
import model.repository.*;
import model.service.*;
import view.panels.admin.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private AdminPacientePanel pacientePanel;
    private AdminMedicoPanel medicoPanel;
    private AdminCitaPanel citaPanel;
    private AdminEnfermeroPanel enfermeroPanel;
    private AdminReportePanel reportePanel;
    private AdminAuditoriaPanel auditoriaPanel;
    private JMenuItem itemLogout;
    private Timer inactividadTimer;

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
        itemLogout.addActionListener(e -> cerrarSesion(usuarioActual));
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
        new CitaController(citaPanel, citaService, pacienteService, medicoService, usuarioActual);

        EnfermeroRepository enfermeroRepo = new EnfermeroRepository();
        EnfermeroService enfermeroService = new EnfermeroService(enfermeroRepo, auditoriaService);
        new EnfermeroController(enfermeroPanel, enfermeroService, usuarioActual);

        ReporteRepository reporteRepo = new ReporteRepository();
        ReporteService reporteService = new ReporteService(reporteRepo);
        new ReporteController(reportePanel, reporteService, usuarioActual);

        new AuditoriaController(auditoriaPanel, auditoriaService);

        // Timer de inactividad
        iniciarTimerInactividad(usuarioActual);
    }

    private void iniciarTimerInactividad(Usuario usuario) {
        inactividadTimer = new Timer(60000, new ActionListener() {
            private int minutosInactivo = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                minutosInactivo++;
                if (minutosInactivo >= 15) {
                    ((Timer)e.getSource()).stop();
                    JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                        "Sesión expirada por inactividad.",
                        "Sesión Expirada",
                        JOptionPane.WARNING_MESSAGE);
                    cerrarSesion(usuario);
                }
            }
        });
        inactividadTimer.start();

        // Resetear timer en cualquier interacción
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent e) {
                int minutosInactivo = 0;
            }
        });
    }

    private void cerrarSesion(Usuario usuario) {
        if (inactividadTimer != null) inactividadTimer.stop();
        new LoginFrame().setVisible(true);
        dispose();
    }

    // Getters
    public JTabbedPane getTabbedPane() { return tabbedPane; }
    public AdminPacientePanel getPacientePanel() { return pacientePanel; }
    public AdminMedicoPanel getMedicoPanel() { return medicoPanel; }
    public AdminCitaPanel getCitaPanel() { return citaPanel; }
    public AdminEnfermeroPanel getEnfermeroPanel() { return enfermeroPanel; }
    public AdminReportePanel getReportePanel() { return reportePanel; }
    public AdminAuditoriaPanel getAuditoriaPanel() { return auditoriaPanel; }
    public JMenuItem getBtnLogout() { return itemLogout; }
}
