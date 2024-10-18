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
import javax.swing.JOptionPane;
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
    boolean selectButtonIsEnable = true;

    public ChooseChar() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting the properties for this frame --jian
        this.setTitle("Choose Character"); // all of these are methods from the parent class JFrame line 36-39 and some other lines below --jian
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // the window is maximized so it covers the whole screen --jian
        this.setResizable(false); // the frame is set to non-resizable because we cannot support resposnive design --jian
        this.setLocationRelativeTo(null); // we set its Layout Manager to Absolute Layout. There are multiple Layout Managers but we used AbsoluteLayout --jian
        // because the components in our game are layered. This Layout Manager is typically not advisable if we wan't a responsive design --jian
        // but we have no choice unless we want to draw everything in our ui manually using hard code --jian
        charType = "knight"; // the default charType is "knight" as observed when we open this window, the first background Image is the knight / lawyer --jian
        addScene(); // calling the method addScene. addScene is a method we created ourselves for this class line 58 --jian

        // echoesObjects instances explained in Main class --jian
        btn_select = createObj(
                "button", (int) (width * 0.7),
                (int) (height * 0.82),
                (int) (width * 0.2),
                (int) (height * 0.14),
                "select_button", false, true, 2
            );
        btn_select.setVisible(true);
        btn_select.addMouseListener(new MouseClickListener(this)); // attaching a mouseListener to our btn_select --jian
        // the MouseClickListener class's parameter is a class that implements MouseInteractable, which is this class  --jian
        scene.add(btn_select);

        addTransparentButton();
    }

    public void addScene() {
        //method called in line 43; creating an instance or object from the SceneBuilder. Note that SceneBuilder extends JPanel, so SceneBuilder can access methods from JPanel, we can treat it as JPanel --jian
        scene = new SceneBuilder("chooseCharacter");
        scene.createScene(); // calling method from SceneBuilder to createScene which includes displaying background and other objects --jian
        scene.initializeCharacter("knight", "knight"); // we initalize our character to knight as a default --jian
        this.add(scene); // again, do not forget to add SwingComponents to a parent Container for it to be visible (in this case, the parent Container is this Frame) --jian
        // to visualize : [parent: JFrame[child: JPanel [child: select_button]]]  --jian
        // this is just an example, but actually, our JPanel or *scene* has many children which are the buttons that are created  --jian
        //using the methods below
        this.setVisible(true); 
        // setting Visibility to true to show our JPanel. without this, we can only see a white background which is jsut the JFrame itself --jian
    }
    public void addTransparentButton() {
        // Position buttons relative to the screen size
        // these instances are objects created with TransparentPanel as its blueprint. --jian
        // unline our EchoesObjects instances, these objects/ custom Swing Panels below are transparent by setting the .setOpaque's boolean/flag to false --jian
        btn_knight = new TransparentPanel((int) (width * 0.03), (int) (height * 0.046), (int) (width * 0.158), (int) (height * 0.28));
        btn_knight.addMouseListener(new MouseClickListener(this));
        btn_knight.setVisible(true);
        scene.add(btn_knight); // again, do not forget to add swing components to a parent container, if you don't it will never be visible --jian

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
        int dynamicFontSize = (int) (height * 0.05); // 5% of the window height --jian
        return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, Math.max(baseFontSize, dynamicFontSize));
    }

    private EchoesObjects createObj(String assetPackage, int x, int y, double width, double height, 
    String type, boolean isAnimated, boolean isState, int numOfSprites){
        // a method that will return EchoesObjects... there's not really much difference if we just call EchoesObjects --jian
        // without this method but it saves us two lines each instantiation :D --jian
        EchoesObjects object = new EchoesObjects(assetPackage, x, y, (int)width, (int)height, type, isAnimated, isState, numOfSprites);
        object.setVisible(true);
        return object;
    }

    private void addPromptNamePanel(){
        promptPanel = createObj(
        "world1",(int) (screenSize.width * 0.20), 
        (int) (screenSize.height * 0.12), 
        (int) (screenSize.width * 0.58), 
        (int) (screenSize.height * 0.7), 
        "namePromptPanel", false, false, 1
        );
        scene.add(promptPanel);  
    }

    private void addNameField(int panelHeight, int panelWidth){
        // Create and add a transparent JTextField to the promptPanel --jian
        nameField = new JTextField(20);
        nameField.setBounds((int) (panelWidth * 0.490), 
                (int) (panelHeight * 0.29), 
                (int) (panelWidth * 0.47), 
                (int) (panelHeight * 0.10));
        nameField.setFont(createDynamicFont(40)); // Font styling --jian
        nameField.setForeground(new Color(238,218,180,255));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        // Make the JTextField transparent
        nameField.setOpaque(false); // This makes the background transparent --jian
        nameField.setBorder(null); 
        nameField.setBackground(new Color(0, 0, 0, 0)); // Optional: Ensure no background color is applied --jian
        promptPanel.add(nameField); 
    }

    private void addOkButton(int panelHeight, int panelWidth){
        btn_ok = createObj(
                "button", (int) (panelWidth * 0.35),
                (int) (panelHeight * 0.45),
                (int) (panelWidth * 0.25),
                (int) (panelHeight * 0.098),
                "ok_button", false, true, 2
            );
            btn_ok.setVisible(true);
            btn_ok.addMouseListener(new MouseClickListener(this));
            promptPanel.add(btn_ok);
    }

    public void addBtnCancel(int panelHeight, int panelWidth){
        btn_cancel = createObj(
                "button", (int) (panelWidth * 0.89),
                (int) (panelHeight * 0.45),
                (int) (panelWidth * 0.25),
                (int) (panelHeight * 0.098),
                "cancel_button", false, true, 2
            );
            btn_cancel.setVisible(true);
            btn_cancel.addMouseListener(new MouseClickListener(this));
            promptPanel.add(btn_cancel);
    }
    @Override
    public void onClick(MouseEvent e) {
        // Since we have many EchoesObjects (buttons) that is attached with a MouseListener, we have to listen --jian
        // which of them caused the event using e.getSource() --jian
        Object source = e.getSource();
        if (source == btn_select) {
            if(!selectButtonIsEnable){ // this prevents the execution of the following lines of code --jian
                // when our promptPanel is Visible --jian
                return;
            }
            selectButtonIsEnable = false;
            addPromptNamePanel(); // if btn_select is clicked, we create the PromptNamePannel using this method --jian
            int width = promptPanel.getWidth();
            int height = promptPanel.getHeight();
            addNameField(width, height);
            addOkButton(width, height);
            addBtnCancel(width, height);
            
            scene.setComponentZOrder(promptPanel, 0);
        } else if (source == btn_knight) {
            charType = "knight";
            scene.character.setCharacterType(charType);
            scene.character.initializeSprites("character_asset", "idle", 18);
            scene.character.setPosX((int)(width * 0.275)); // Update position based on width
            scene.character.setPosY((int)(height * 0.35)); // Update position based on height
            scene.currentSceneIndex = 0;
        } else if (source == btn_priest) {
            charType = "priest";
            scene.character.setCharacterType(charType);
            scene.character.initializeSprites("character_asset", "idle", 18);
            scene.character.setPosX((int)(width * 0.3)); // Update position based on width
            scene.character.setPosY((int)(height * 0.35)); // Update position based on height
            scene.currentSceneIndex = 1;
        } else if (source == btn_wizard) {
            charType = "wizard";
            scene.character.setCharacterType(charType);
            scene.character.initializeSprites("character_asset", "idle", 18);
            scene.character.setPosX((int)(width * 0.3)); // Update position based on width
            scene.character.setPosY((int)(height * 0.35)); // Update position based on height
            scene.currentSceneIndex = 2;
        }else if(source == btn_ok){
            if((nameField.getText().trim().isEmpty())){ // a condition that sends a warning message to the user if they clicked ok when they didn't enter a name --jian
                JOptionPane.showMessageDialog(null, "Please enter a name!", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
                World1 window = new World1(charType, nameField.getText());
                System.out.println(nameField.getText());
                window.setVisible(true);
                this.setVisible(false);
            
        }else if(source == btn_cancel){ 
            promptPanel.setVisible(false);  // setting the promptPanel's visibility to false if cancel is clicked --jian
            selectButtonIsEnable = true; // this allows us to click our btn_select again because promptPanel is closed --jian
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
