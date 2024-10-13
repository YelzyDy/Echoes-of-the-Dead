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
    EchoesButton btn_select;
    EchoesButton btn_ok;
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
        btn_select = addSelectButton("SELECT", 50, 60, Color.BLACK, new Color(180, 148, 124), new Color(75, 84, 122), new Color(125, 132, 157), new Color(180, 148, 124), width * 0.7, height * 0.85, width * 0.2, height * 0.1);
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

    public EchoesButton addSelectButton(String text, int radius, int baseFontSize, Color color, Color foreGround, 
                                        Color colorOver, Color colorClick, Color BorderColor,
                                        double x, double y, double width, double height) {
        EchoesButton btn = new EchoesButton();
        btn.setFont(createDynamicFont(baseFontSize));
        btn.setForeground(foreGround);
        btn.setText(text);
        btn.setOpaque(true);
        btn.setColor(color);
        btn.setColorOver(colorOver);
        btn.setRadius(radius);
        btn.setColorClick(colorClick);
        btn.setBorderColor(BorderColor);
        // Set bounds based on screen size
        btn.setBounds((int)x, (int)y, (int)width, (int)height);

        btn.setVisible(true);
        btn.addMouseListener(new MouseClickListener(this));
        return btn;
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
            EchoesObjects promptPanel = new EchoesObjects(
            (int) (screenSize.width * 0.20), 
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
            btn_ok = new EchoesButton();
            btn_ok = addSelectButton("OK", 50, 40, new Color(39,75,84), new Color(235,195,150,255), new Color(29,70,78,255), 
                    new Color(4,33,40), new Color(237,196,150,255), panelWidth * 0.43, panelHeight * 0.68, panelWidth * 0.15, panelHeight * 0.12);          
            promptPanel.add(btn_ok);
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
            World1 window = new World1(charType, nameField.getText());
            window.setVisible(true);
            this.setVisible(false);
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
