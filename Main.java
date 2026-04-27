import javax.swing.*;

// Точка входа — только создаёт и запускает форму заказа
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}
