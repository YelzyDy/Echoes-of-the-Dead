package echoes.of.the.dead;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Main extends javax.swing.JFrame implements MouseInteractable {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    public Main() {
        // Configure JFrame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);  // Absolute layout for positioning

        // Create custom JPanel that will paint the background
        EchoesObjects backgroundPanel = new EchoesObjects(
            "title_screen", 0, 0, width, height, "titleScreen", false, false,0);
        backgroundPanel.setVisible(true);

        EchoesObjects btn_title_play = new EchoesObjects(
                "button", (int) (screenSize.width * 0.75),
                (int) (screenSize.height * 0.82),
                (int) (screenSize.width * 0.21),
                (int) (screenSize.height * 0.13),
                "title_button", false, true, 0
            );
        
        btn_title_play.setVisible(true);
        btn_title_play.addMouseListener(new MouseClickListener(this));
        this.add(btn_title_play); 
        this.add(backgroundPanel); 
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);  // Create and display the Main window
        });
    }

    @Override
    public void onClick(MouseEvent e) {
        ChooseChar window = new ChooseChar();
        window.setVisible(true);
        this.setVisible(false);
    }

    @Override
    public void onHover(MouseEvent e) {

    }

    @Override
    public void onExit(MouseEvent e) {

    }
}
