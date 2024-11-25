package EOD.scenes;
import EOD.Ending;
import EOD.dialogues.Dialogues;
import EOD.dialogues.FullScreenDialogues;
import EOD.worlds.World;
import java.awt.*;
import javax.swing.*;

public class ChoiceUI extends JPanel {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) (screenSize.width * 0.95);
    private final int height = (int) (screenSize.height * 0.3);
    private final JPanel choicePanel;
    private boolean isKillerFound;
    private World world;
    private String[] buttonNames = {
        "Faithful", "Ruby", "Akefay",
        "Monologuer", "Natty", "Asriel",
        "Renegald", "Chea", "Yoo"
    };
    private JLayeredPane layeredPane;
    
    public ChoiceUI(World world) {
        this.world = world;
        this.layeredPane = world.getLayeredPane();
            // Main panel setup
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.BLACK);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            //setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            setLayout(new BorderLayout(0, 20)); // Add vertical gap between components
            setVisible(false);
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BorderLayout(0, 20));
            containerPanel.setBackground(Color.BLACK);
            containerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    
            // Text panel at the top
            JPanel textPanel = new JPanel();
            textPanel.setBackground(Color.BLACK);
            textPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            textPanel.setPreferredSize(new Dimension(width, height / 3));
            
            // Dialogue text
            JLabel dialogueLabel = new JLabel("<html><center>SO WHO DO YOU THINK IT IS?</center></html>");
            dialogueLabel.setFont(new Font("Arial", Font.BOLD, 24));
            dialogueLabel.setForeground(Color.WHITE);
            dialogueLabel.setHorizontalAlignment(SwingConstants.CENTER);
            textPanel.add(dialogueLabel);
            
            // Choice buttons panel
            choicePanel = new JPanel();
            choicePanel.setLayout(new GridLayout(3, 3, 20, 10)); // Increased spacing
            choicePanel.setBackground(Color.BLACK);
            choicePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            FullScreenDialogues dialogues = new FullScreenDialogues();
            dialogues.setPlayerType(world.getPlayer().getCharacterType()); 
    
            // Create and add 9 buttons
            for (int i = 0; i < 9; i++) {
                JButton button = new JButton(buttonNames[i]);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                button.setFocusPainted(false);
                button.setFont(new Font("Arial", Font.PLAIN, 18));
    
                button.addActionListener(e -> {
                    System.out.println(button.getText() + " button clicked!");
                    
                    if ((world.getPlayer().getCharacterType().equals("wizard") && button.getText().equals("Faithful")) ||
                        (world.getPlayer().getCharacterType().equals("priest") && button.getText().equals("Natty")) ||
                        (world.getPlayer().getCharacterType().equals("knight") && button.getText().equals("Yoo"))) {
                        Dialogues script = new Dialogues();
                        script.setKiller(world.getScene().enemyList.get(1));
                        //dialogues.displayDialogue(1);
                        isKillerFound = true;
                        setVisible(false); // Hide choice UI
                        SceneBuilder scene = world.getScene();
                        scene.setCurrentSceneIndex(5);

                        
                        switch (world.getPlayer().getCharacterType()){
                            case "knight":
                                script.displayDialogues(21, world);
                                break;
                            case "priest":
                                script.displayDialogues(20, world);
                                break;
                            case "wizard":
                                script.displayDialogues(19, world);
                                break; 
                        }
                    } else {
                        dialogues.displayDialogue(2);
                        isKillerFound = false;
                        new Ending(false).setVisible(true);
                        world.setVisible(false);
                    }
                });

            choicePanel.add(button);
        }
        
        // Add components to main panel
        containerPanel.add(textPanel, BorderLayout.NORTH);
        containerPanel.add(choicePanel, BorderLayout.CENTER);
        add(containerPanel, BorderLayout.CENTER);
        
        // Add padding around the entire panel
        setBorder(BorderFactory.createCompoundBorder(
            getBorder(),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    public boolean getIsKillerFound() {
        return isKillerFound;
    }
}