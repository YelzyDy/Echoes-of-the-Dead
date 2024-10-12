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

/**
 *
 * @author Joana
 */
public class ChooseChar extends javax.swing.JFrame implements MouseInteractable {
    SceneBuilder scene;
    TransparentPanel btn_knight;
    TransparentPanel btn_wizard;
    TransparentPanel btn_priest;
    EchoesButton btn_select;
    String charType;
    
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
        addSelectButton();
        addTransparentButton();
    }

    public void addScene() {
        scene = new SceneBuilder("chooseCharacter");
        scene.createScene();
        scene.initializeCharacter("knight");
        this.add(scene);
        this.setVisible(true);
    }

    public void addSelectButton() {
        btn_select = new EchoesButton();
        btn_select.setFont(createDynamicFont());
        btn_select.setForeground(new Color(180, 148, 124));
        btn_select.setText("SELECT");
        btn_select.setOpaque(true);
        btn_select.setColor(Color.BLACK);
        btn_select.setColorOver(new Color(75, 84, 122));
        btn_select.setRadius(50);
        btn_select.setColorClick(new Color(125, 132, 157));
        btn_select.setBorderColor(new Color(180, 148, 124));
        // Set bounds based on screen size
        btn_select.setBounds((int) (width * 0.7), (int) (height * 0.85), (int) (width * 0.2), (int) (height * 0.1));
        
        btn_select.setVisible(true);
        scene.add(btn_select);
        btn_select.addMouseListener(new MouseClickListener(this));
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

    private java.awt.Font createDynamicFont() {
        // Adjust the font size based on window height
        int baseFontSize = 60; // Base font size that looks good
        int dynamicFontSize = (int) (height * 0.05); // 5% of the window height
        return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, Math.max(baseFontSize, dynamicFontSize));
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if (source == btn_select) {
            World1 window = new World1(charType);
            System.out.println(charType);
            this.setVisible(false);
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
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
