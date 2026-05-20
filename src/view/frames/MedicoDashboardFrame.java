package view.frames;

import controller.MedicoAgendaController;
import controller.MedicoPacienteController;
import model.entity.Usuario;
import model.repository.*;
import model.service.*;
import view.panels.medico.MedicoAgendaPanel;
import view.panels.medico.MedicoPacientePanel;
import view.panels.medico.MedicoConsultaPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicoDashboardFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private MedicoAgendaPanel agendaPanel;
    private MedicoPacientePanel pacientePanel;
    private MedicoConsultaPanel consultaPanel;
    private JMenuItem itemLogout;
    private Timer inactividadTimer;

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
        itemLogout.addActionListener(e -> cerrarSesion(usuarioActual));
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

        ConsultaRepository consultaRepo = new ConsultaRepository();
        ConsultaService consultaService = new ConsultaService(consultaRepo, auditoriaService);

        new MedicoPacienteController(pacientePanel, pacienteService, consultaService, citaService, usuarioActual);

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
                    JOptionPane.showMessageDialog(MedicoDashboardFrame.this,
                        "Sesión expirada por inactividad.",
                        "Sesión Expirada",
                        JOptionPane.WARNING_MESSAGE);
                    cerrarSesion(usuario);
                }
            }
        });
        inactividadTimer.start();
    }

    private void cerrarSesion(Usuario usuario) {
        if (inactividadTimer != null) inactividadTimer.stop();
        new LoginFrame().setVisible(true);
        dispose();
    }

    public MedicoAgendaPanel getAgendaPanel() { return agendaPanel; }
    public MedicoPacientePanel getPacientePanel() { return pacientePanel; }
    public MedicoConsultaPanel getConsultaPanel() { return consultaPanel; }
    public JMenuItem getBtnLogout() { return itemLogout; }
}
