package controller;

import model.entity.Usuario;
import model.enums.Rol;
import model.service.AuthService;
import view.frames.AdminDashboardFrame;
import view.frames.LoginFrame;
import view.frames.MedicoDashboardFrame;

import javax.swing.*;

public class AuthController {
    private final LoginFrame loginFrame;
    private final AuthService authService;

    public AuthController(LoginFrame loginFrame, AuthService authService) {
        this.loginFrame = loginFrame;
        this.authService = authService;
        iniciarListeners();
    }

    private void iniciarListeners() {
        loginFrame.getBtnLogin().addActionListener(e -> {
            String usuario = loginFrame.getTxtUsuario().getText();
            String password = new String(loginFrame.getTxtPassword().getPassword());

            Usuario user = authService.login(usuario, password);

            if (user == null) {
                JOptionPane.showMessageDialog(loginFrame,
                        "Usuario o contraseña incorrectos",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE);
            } else if (user.getRol() == Rol.ADMINISTRADOR) {
                SwingUtilities.invokeLater(() -> {
                    new AdminDashboardFrame(user).setVisible(true);
                    loginFrame.dispose();
                });
            } else if (user.getRol() == Rol.MEDICO) {
                SwingUtilities.invokeLater(() -> {
                    new MedicoDashboardFrame(user).setVisible(true);
                    loginFrame.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(loginFrame,
                        "Rol no autorizado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}