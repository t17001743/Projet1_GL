import javax.swing.*;

public class Interface {
    public static void main(String[] args) {
        // D'après le tutoriel Swing, c'est plus performant de créer un thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // On crée une instance de notre fenêtre
                Window window = new Window();
                // On la rend visible
                window.setVisible(true);
            }
        });
    }
}
