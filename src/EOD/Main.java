package EOD;

import EOD.dialogues.*;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.SettingsWindow;
import EOD.utils.BGMPlayer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Main extends javax.swing.JFrame implements MouseInteractable {
    FullScreenDialogues dialogues = new FullScreenDialogues();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;
    BGMPlayer bgmPlayer;

    public Main() {
        // Configure JFrame
        bgmPlayer = BGMPlayer.getInstance();  // Use singleton instance
        bgmPlayer.playBGM("src/old/audio_assets/selection.wav");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);  // Absolute layout for positioning
        this.setTitle("Title Screen");

        // Create custom JPanel that will paint the background
        // EchoesObjects is a *class* that serves as a blueprint for all the objects that we will display on screen i.e shop and portals -- jian
        // in line 23 - 25, we will used EchoesObjects to display our background -- jian
        // its parameters are the name of the assetPackage, x, y, width, height, type, isAnimated Boolean, isState Boolean, -- jian
        // and numberOfSprites in that order -- jian
        // our backgroundPanel doesn't have animation, it doesn't have a state(meaning on or off/ hover or not hovered), and -- jian
        EchoesObjects backgroundPanel = new EchoesObjects(
            "title_screen", 0, 0, width, height, "titleScreen", false, false,1);
        // we set our backgroundPanel's visibility to true since by default, its class (EchoesObjects) had set its visibility to false -- jian
        // as our EchoesObjects extends to TransparentPanel, it's superclass -- jian
            backgroundPanel.setVisible(true);

        // we use the same class or blueprint for our object btn_title_play, but it's isState is true this time -- jian
        // meaning that there are on or off states / hovered or not hovered state / mouse OnHover or mouse onExit -- jian
        // note that the String assetPackage and String type is essential for accessing the pathName of the image we will use -- jian
        // images are the core of our EchoesObjects which will be explained in the EchoesObjects class -- jian
        EchoesObjects btn_title_play = new EchoesObjects(
                "button", (int) (screenSize.width * 0.75),
                (int) (screenSize.height * 0.82),
                (int) (screenSize.width * 0.21),
                (int) (screenSize.height * 0.13),
                "title_button", false, true, 2
            );
        // just like the backgroundPanel, we set its visibility to true since TransparentPanel, its parent class is --jian
        // initially false --jian
        btn_title_play.setVisible(true);
        // another difference between the backgroundPanel and the btn_title_play is that btn_title_play has --jian
        // a MouseListener attached which listens to to mouseEvents that will occur within the bouds of btn_title_play --jian
        btn_title_play.addMouseListener(new MouseClickListener(this));
        btn_title_play.setName("play");
      
        // do not forget to add our custom JPanel objects to our JFrame/Jpanel or whatever parent container it should have --jian
        // in this case, the parent container is the JFrame and the children of JFrame is the objects we instantiated --jian
        // Create settings button
        // Add components in reverse order (background should be added last)
        EchoesObjects btn_settings = new EchoesObjects(
                "settings", 
                (int) (screenSize.width * 0.01),
                (int) (screenSize.height * 0.01),
                (int) (screenSize.width * 0.07),
                (int) (screenSize.height * 0.11),
                "settings_button", 
                false, 
                true, 
                2
            );
        btn_settings.setVisible(true);
        btn_settings.addMouseListener(new MouseClickListener(this));
        btn_settings.setName(("settings"));

        this.add(btn_settings);
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
        EchoesObjects clickedButton = (EchoesObjects) e.getSource();

        if ("settings".equals(clickedButton.getName())){
            SettingsWindow settingsWindow = SettingsWindow.getInstance(bgmPlayer, "null");
            settingsWindow.setVisible(true); // Display the window

        } else if ("play".equals(clickedButton.getName())){
            bgmPlayer.stopBGM();
            bgmPlayer.playBGM("src/audio_assets/exposition.wav");
            dialogues.displayDialogue(0);
            bgmPlayer.stopBGM();
            ChooseChar window = new ChooseChar();
            window.setVisible(true);
            this.setVisible(false);
        }
    }

    @Override
    public void onHover(MouseEvent e) { // These are all abstract methods from the MouseInteractable -- jian

    }

    @Override
    public void onExit(MouseEvent e) { // These are all abstract methods from the MouseInteractable -- jian

    }
}
