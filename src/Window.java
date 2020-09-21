import javax.swing.*;

// Cette classe est responsable de la création de la fenêtre graphique
public class Window extends JFrame {

    public Window() {
        super();
        build();
    }

    // Cette méthode construit la fenêtre
    private void build() {
        // On donne un titre à la fenêtre
        setTitle("Elevator Simulator 2020");
        // On définit la taille de la fenêtre
        setSize(500, 500);
        // On centre la fenêtre sur l'écran
        setLocationRelativeTo(null);
        // On interdit le redimensionnement de la fenêtre
        setResizable(false);
        // On indique à l'application de se fermer lorsqu'on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
