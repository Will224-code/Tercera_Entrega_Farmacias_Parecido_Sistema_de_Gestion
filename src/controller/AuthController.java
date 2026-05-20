package controller;

import model.entity.Usuario;
import model.enums.Rol;
import model.service.AuthService;
import view.frames.AdminDashboardFrame;
import view.frames.LoginFrame;
import view.frames.MedicoDashboardFrame;
import view.Components.NotificationToast;

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
            String usuario = loginFrame.getTxtUsuario().getText().trim();
            String password = new String(loginFrame.getTxtPassword().getPassword());

            if (usuario.isEmpty() || password.isEmpty()) {
                NotificationToast.show(loginFrame, "Usuario y contraseña son obligatorios", "error");
                return;
            }

            Usuario user = authService.login(usuario, password);

            if (user == null) {
                NotificationToast.show(loginFrame,
                        "Usuario o contraseña incorrectos",
                        "error");
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
                NotificationToast.show(loginFrame,
                        "Rol no autorizado",
                        "error");
            }
        });

        // Login con tecla Enter
        loginFrame.getTxtPassword().addActionListener(e -> {
            loginFrame.getBtnLogin().doClick();
        });
    }
}
