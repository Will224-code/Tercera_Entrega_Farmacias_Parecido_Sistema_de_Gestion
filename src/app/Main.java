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
}