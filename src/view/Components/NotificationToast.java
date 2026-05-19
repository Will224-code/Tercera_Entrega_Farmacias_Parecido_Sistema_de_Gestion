package view.Components;

import javax.swing.*;
import java.awt.*;

public class NotificationToast {
    public static void show(Component parent, String message, String type) {
        JOptionPane.showMessageDialog(parent, message,
                type.equals("error") ? "Error" : "Información",
                type.equals("error") ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
    }

    public static void show(Component parent, String message) {
        show(parent, message, "info");
    }
}
