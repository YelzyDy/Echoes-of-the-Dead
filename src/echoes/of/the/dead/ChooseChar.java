/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Joana
 */
public class ChooseChar extends javax.swing.JFrame implements MouseInteractable {
    SceneBuilder scene;
    TransparentPanel btn_knight;
    TransparentPanel btn_wizard;
    TransparentPanel btn_priest;
    EchoesObjects btn_select;
    EchoesObjects btn_ok;
    EchoesObjects btn_cancel;
    EchoesObjects promptPanel;
    String charType;
    JTextField nameField;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    public ChooseChar() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Choose Character");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        charType = "knight";
        addScene();

        btn_select = new EchoesObjects(
                "button", (int) (width * 0.7),
                (int) (height * 0.82),
                (int) (width * 0.2),
                (int) (height * 0.14),
                "select_button", false, true
            );
        btn_select.setVisible(true);
        btn_select.addMouseListener(new MouseClickListener(this));
        scene.add(btn_select);
        addTransparentButton();
    }

    public void addScene() {
        scene = new SceneBuilder("chooseCharacter");
        scene.createScene();
        scene.initializeCharacter("knight", "knight");
        this.add(scene);
        this.setVisible(true);
    }
    public void addTransparentButton() {
        // Position buttons relative to the screen size
        btn_knight = new TransparentPanel((int) (width * 0.03), (int) (height * 0.046), (int) (width * 0.158), (int) (height * 0.28));
        btn_knight.addMouseListener(new MouseClickListener(this));
        btn_knight.setVisible(true);
        scene.add(btn_knight);

        btn_wizard = new TransparentPanel((int) (width * 0.03), (int) (height * 0.359), (int) (width * 0.158), (int) (height * 0.28));
        btn_wizard.addMouseListener(new MouseClickListener(this));
        btn_wizard.setVisible(true);
        scene.add(btn_wizard);

        btn_priest = new TransparentPanel((int) (width * 0.03), (int) (height * 0.674), (int) (width * 0.158), (int) (height * 0.28));
        btn_priest.addMouseListener(new MouseClickListener(this));
        btn_priest.setVisible(true);
        scene.add(btn_priest);
    }

    private java.awt.Font createDynamicFont(int baseFontSize) {
        // Adjust the font size based on window height
        int dynamicFontSize = (int) (height * 0.05); // 5% of the window height
        return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, Math.max(baseFontSize, dynamicFontSize));
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if (source == btn_select) {
            promptPanel = new EchoesObjects(
            "world1",(int) (screenSize.width * 0.20), 
            (int) (screenSize.height * 0.12), 
            (int) (screenSize.width * 0.58), 
            (int) (screenSize.height * 0.7), 
            "namePromptPanel", false, false
            );
        
            promptPanel.setVisible(true);
            int panelWidth = promptPanel.getWidth();
            int panelHeight = promptPanel.getHeight();
        // Create and add a transparent JTextField to the promptPanel
            nameField = new JTextField();
            nameField.setBounds((int) (panelWidth * 0.293), 
                    (int) (panelHeight * 0.45), 
                    (int) (panelWidth * 0.42), 
                    (int) (panelHeight * 0.10));
            nameField.setFont(createDynamicFont(40)); // Font styling
        
            // Make the JTextField transparent
            nameField.setOpaque(false); // This makes the background transparent
            nameField.setBorder(null); 
            nameField.setForeground(Color.WHITE); // Set text color to white (or any other color for contrast)
            nameField.setBackground(new Color(0, 0, 0, 0)); // Optional: Ensure no background color is applied
            btn_ok = new EchoesObjects(
                "button", (int) (panelWidth * 0.24),
                (int) (panelHeight * 0.68),
                (int) (panelWidth * 0.22),
                (int) (panelHeight * 0.13),
                "ok_button", false, true
            );
            btn_ok.setVisible(true);
            btn_ok.addMouseListener(new MouseClickListener(this));
            promptPanel.add(btn_ok);

            btn_cancel = new EchoesObjects(
                "button", (int) (panelWidth * 0.55),
                (int) (panelHeight * 0.68),
                (int) (panelWidth * 0.22),
                (int) (panelHeight * 0.13),
                "cancel_button", false, true
            );
            btn_cancel.setVisible(true);
            btn_cancel.addMouseListener(new MouseClickListener(this));
            promptPanel.add(btn_cancel);

            promptPanel.add(nameField); // Add the text field to the prompt panel
            scene.add(promptPanel); // Add the prompt panel to the scene
        
        } else if (source == btn_knight) {
            charType = "knight";
            scene.character.setCharacterType(charType);
            scene.character.initializeIdleSprites(18);
            scene.character.setPosX((int)(width * 0.32)); // Update position based on width
            scene.character.setPosY((int)(height * 0.51)); // Update position based on height
            scene.currentPanelIndex = 0;
        } else if (source == btn_priest) {
            charType = "priest";
            scene.character.setCharacterType(charType);
            scene.character.initializeIdleSprites(18);
            scene.character.setPosX((int)(width * 0.349)); // Update position based on width
            scene.character.setPosY((int)(height * 0.49)); // Update position based on height
            scene.currentPanelIndex = 1;
        } else if (source == btn_wizard) {
            charType = "wizard";
            scene.character.setCharacterType(charType);
            scene.character.initializeIdleSprites(18);
            scene.character.setPosX((int)(width * 0.34)); // Update position based on width
            scene.character.setPosY((int)(height * 0.51)); // Update position based on height
            scene.currentPanelIndex = 2;
        }else if(source == btn_ok){
            if(!(nameField.getText().trim().isEmpty())){
                World1 window = new World1(charType, nameField.getText());
                System.out.println(nameField.getText());
                window.setVisible(true);
                this.setVisible(false);
            }
        }else if(source == btn_cancel){        
                promptPanel.setVisible(false);    
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
