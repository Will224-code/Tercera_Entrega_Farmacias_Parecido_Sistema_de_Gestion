package app;

import controller.AuthController;
import model.repository.AuditoriaRepository;
import model.repository.UsuarioRepository;
import model.service.AuditoriaService;
import model.service.AuthService;
import view.frames.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configurar look and feel
            configurarLookAndFeel();

            // Repositorios
            UsuarioRepository usuarioRepository = new UsuarioRepository();
            AuditoriaRepository auditoriaRepository = new AuditoriaRepository();

            // Servicios
            AuditoriaService auditoriaService = new AuditoriaService(auditoriaRepository);
            AuthService authService = new AuthService(usuarioRepository, auditoriaService);

            // Vista
            LoginFrame loginFrame = new LoginFrame();

            // Controlador
            new AuthController(loginFrame, authService);

            loginFrame.setVisible(true);
        });
    }

    private static void configurarLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error configurando Look and Feel: " + e.getMessage());
        }
    }
}
