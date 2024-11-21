package EOD.dialogues;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.util.ArrayList;
public class QuestsDialogues extends JFrame{
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected StoryLine story = new StoryLine();
    protected String[] currentDialogue;
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.4);
    private String playerName;
    private String worldType;
    private JDialog dialog;
    protected JScrollPane scrollPane;
    private Dialogues dialogues;
    private int i = 0;
    protected boolean isQuestDialoguesActive;
    private int objectiveIndex = 0;
    private java.util.List<JButton> optionButtons;
    private JPanel buttonPanel;

    public QuestsDialogues(){
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.BLACK);
    
        optionButtons = new ArrayList<>();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setWorldType(String worldType) {
        this.worldType = worldType;
    }

    public int getObjectiveIndex(){
        return objectiveIndex;
    }

    public void openScrollableOptions(int ID, Dialogues dialogues, JLabel textBox) {
        // LOAD NPC
        dialog = dialogues.getStoryJDialog();
        dialogues.pressToContinueLabel.setVisible(false);
        this.dialogues = dialogues;
        isQuestDialoguesActive = true;
        story.setPlayerName(playerName);
        i = 0;
        switch (ID) {
            case 1:
                story.missConstanceQuests(); break;
            case 4:
                story.migginsQuests(worldType); break;
            case 5:
            default:
            break;
        }

        dialog.getContentPane().setBackground(Color.BLACK);
        dialog.add(textBox, BorderLayout.CENTER);

        // SCROLL PANEL
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 0;

        optionButtons.clear();
        buttonPanel.removeAll();

        // SCROLL WINDOW & PANE
        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.WHITE;
                this.trackColor = Color.BLACK;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Color.BLACK);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Color.BLACK);
                return button;
            }
        });
        // SET SCROLL BUTTONS
        String[] options = story.getQArr();

        for (int i = 0; i < options.length; i++) {
            if (options[i] != null) {
                JButton optionButton = new JButton("<html><center>" + options[i] + "</center></html>");
                optionButton.setFont(new Font("Monospaced", Font.PLAIN, 28));
                optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                optionButton.setForeground(Color.WHITE);
                optionButton.setBackground(Color.BLACK);
                optionButton.setFocusPainted(false);
                optionButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                optionButtons.add(optionButton);

                int preferredHeight = optionButton.getPreferredSize().height * 3;
                optionButton.setPreferredSize(new Dimension(0, preferredHeight));
                optionButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, preferredHeight));
                optionButton.setActionCommand(String.valueOf(i));

                optionButton.addActionListener(e -> {
                    int clickedIndex = Integer.parseInt(e.getActionCommand());
                    objectiveIndex = clickedIndex;
                    dialog.remove(scrollPane);
                    dialogues.pressToContinueLabel.setVisible(true);
                    dialog.revalidate();
                    dialog.repaint();
                    dialogues.buttonPanel.setVisible(true);
                
                    if(clickedIndex == 0){
                        if (story.getSize() > 0) {
                            dialogues.typewriterEffect(story.getLine(0));
                            currentDialogue = story.getArr();
                            this.i++;
                        }
                    }else{
                        int c = 0;
                        for(int j = 0, count = 1; j < story.getSize("oarr"); j++){
                            if(story.getOArr()[j].equals("-")){
                                count++;
                            }
                            if(clickedIndex == count){
                                break;
                            }
                            c++;
                        }
                        int targetIndex = (clickedIndex != 1) ? c + 1 : 0;
                        dialogues.typewriterEffect(story.getLine(targetIndex, story.getOArr()));
                        this.i = targetIndex + 1;
                        currentDialogue = story.getOArr();
                    }
                });

                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.BOTH;
                buttonPanel.add(optionButton, gbc);
            }
        }

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.getContentPane().setBackground(Color.BLACK);
        dialog.setLocation(x, y);
        dialog.setVisible(true);
    }

    public void updateObjectivesAtIndex(int index, boolean value){
        story.getObjDoneArray()[index] = value;
    }

    private String getCurrentStringArray(){
        if(currentDialogue == story.getArr()){
            return "arr";
        }else if(currentDialogue == story.getOArr()){
            return "oarr";
        }else if(currentDialogue == story.getQArr()){
            return "qarr";
        }
        return null;
    }

    public void removeOptionButton(int index) {
        if (index >= 0 && index < optionButtons.size()) {
            // Remove the button from the panel
            JButton buttonToRemove = optionButtons.get(index);
            buttonPanel.remove(buttonToRemove);
            
            // Remove from our list of buttons
            optionButtons.remove(index);
            
            // Rebuild the panel layout with remaining buttons
            buttonPanel.removeAll();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            gbc.gridx = 0;
            
            // Re-add remaining buttons with updated positions
            for (int i = 0; i < optionButtons.size(); i++) {
                gbc.gridy = i;
                buttonPanel.add(optionButtons.get(i), gbc);
            }
            
            // Update the visual state
            buttonPanel.revalidate();
            buttonPanel.repaint();
            
            // Signal that we need to update the dialog
            if (dialog != null) {
                dialog.revalidate();
                dialog.repaint();
            }
        }
    }
    

    public void handleSetText() {
        int size = story.getSize(getCurrentStringArray());
        JLabel textBox = dialogues.textBox;
        System.out.println("handleSetText called");
        System.out.println("Current i: " + i);
        System.out.println("isTyping: " + dialogues.isTyping);
        if (dialogues.isTyping) {
            // If typing is in progress, interrupt it and show the full text immediately
            dialogues.isTyping = false;
            if (i < size) {
                i -= 1;
                textBox.setText(story.getLine(i++, currentDialogue));
            }
            return;
        }
        System.out.println("o index: " + objectiveIndex);
    
        if (i < size && !currentDialogue[i].equals("-")) {
            // Start the typewriter effect for the next line
            dialogues.typewriterEffect(story.getLine(i, currentDialogue));
            i++;
        } else {
            dialogues.getStoryJDialog().dispose();
            isQuestDialoguesActive = false;
            if(currentDialogue == story.getArr()){
                dialogues.npc.doneQDialogues = true;
            }else if(currentDialogue == story.getOArr() && story.getObjDoneArray()[objectiveIndex - 1]){
                dialogues.npc.doneODialogues[objectiveIndex - 1] = true;
            }
            return;
        }
    }
}