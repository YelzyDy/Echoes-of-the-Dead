package EOD;

import EOD.gameInterfaces.MouseInteractable;
import EOD.gameInterfaces.WindowInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import EOD.utils.SFXPlayer;
import EOD.worlds.World;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Ending extends javax.swing.JFrame implements MouseInteractable, WindowInteractable {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    private BGMPlayer bgmPlayer = BGMPlayer.getInstance();
    private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    private boolean isGoodEnding;
    private World world;

    // Constructor for Good or Bad Ending
    public Ending(boolean isGoodEnding, World world) {
        this.world = world;
        this.isGoodEnding = isGoodEnding;

        // Set up basic frame properties
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("Ending Screen");

        // Choose background based on ending type
        String backgroundType = isGoodEnding ? "goodEnd" : "badEnd";
        String bgmPath = isGoodEnding 
            ? "src/audio_assets/bgm/goodendingbgm.wav" 
            : "src/audio_assets/bgm/badendingbgm.wav";
        
        // Play appropriate BGM
        bgmPlayer.playBGM(bgmPath);

        // Create background panel
        EchoesObjects backgroundPanel = new EchoesObjects(
                "ending", 0, 0, width, height, backgroundType, false, false, 1);
            backgroundPanel.setVisible(true);

        // Create Play Again button
        EchoesObjects btn_play_again = new EchoesObjects(
            "ending", 
            (int) (screenSize.width * 0.4),
            (int) (screenSize.height * 0.82),
            (int) (screenSize.width * 0.21),
            (int) (screenSize.height * 0.13),
            "playAgainButton", 
            false, true, 2
        );
        btn_play_again.setVisible(true);
        btn_play_again.addMouseListener(new MouseClickListener(this));
        btn_play_again.setName("play again");

        // Create Exit button
        EchoesObjects btn_exit = new EchoesObjects(
            "ending", 
            (int) (screenSize.width * 0.75),
            (int) (screenSize.height * 0.82),
            (int) (screenSize.width * 0.21),
            (int) (screenSize.height * 0.13),
            "exitEndButton", 
            false, true, 2
        );
        btn_exit.setVisible(true);
        btn_exit.addMouseListener(new MouseClickListener(this));
        btn_exit.setName("exit");

        // Add buttons and background to frame
        this.add(btn_play_again);
        this.add(btn_exit);
        this.add(backgroundPanel);
    }

    @Override
    public void onClick(MouseEvent e) {
        EchoesObjects clickedButton = (EchoesObjects) e.getSource();
        sfxPlayer = SFXPlayer.getInstance();
        sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");

        SceneBuilder scene = world.getScene();
        scene.gameLoopTimer.stop();
        scene.free();

        if ("play again".equals(clickedButton.getName())) {
            bgmPlayer.stopBGM();
            this.dispose(); // Close current ending screen
            new Main().setVisible(true); 
        } else if ("exit".equals(clickedButton.getName())) {
            // Terminate the whole program
            System.exit(0);
        }
    }

    @Override
    public void onHover(MouseEvent e) {

    }

    @Override
    public void onExit(MouseEvent e) {

    }

    @Override
    public void onWindowClosing(WindowEvent e) {
        bgmPlayer.stopBGM();
    }
}