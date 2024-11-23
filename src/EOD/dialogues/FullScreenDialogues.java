package EOD.dialogues;

import EOD.utils.SFXPlayer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class FullScreenDialogues extends JFrame {
    private static final long serialVersionUID = 1L;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    StoryLine story = new StoryLine();
    private final int width = screenSize.width;
    private final int height = (int)(screenSize.height * 0.97);
    private final int x = 0;
    private final int y = 23;
    private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    private String playerType;
    private JTextPane textBox;
    protected JLabel pressToContinueLabel;
    private int i = 0;
    private int size = 0;
    private volatile boolean isTyping = false;
    private Thread typewriterThread = null;
    private SimpleAttributeSet textAttributes;
    private String currentFullText = "";
    private int ID;
    private JDialog storyDialogue;
    private int index;

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public void displayDialogue(int ID) {
        // Initialize text attributes
        textAttributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(textAttributes, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontFamily(textAttributes, "Monospaced");
        StyleConstants.setFontSize(textAttributes, 28);
        StyleConstants.setForeground(textAttributes, Color.WHITE);

        this.ID = ID;

        switch (ID) {
            case 0: story.exposition();
                break;
            case 1: story.postEnding(playerType);
                break;
            case 2: story.badEnding();
                break;
            default:
                break;
        }

        pressToContinueLabel = new JLabel("Press Here to Continue", SwingConstants.CENTER);
        pressToContinueLabel.setFont(new Font("Monospaced", Font.PLAIN, (int) (screenSize.width * 0.01)));
        pressToContinueLabel.setForeground(Color.WHITE);
        pressToContinueLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (screenSize.height * 0.1), 0));
        pressToContinueLabel.setVisible(true);

        // THE WINDOW
        storyDialogue = new JDialog(this, "ECHOES OF THE DEAD", Dialog.ModalityType.APPLICATION_MODAL);
        storyDialogue.setUndecorated(true);
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());

        // Create custom text pane that prevents text selection
        textBox = new JTextPane() {
            @Override
            public void paste() {} // Disable paste
            
            @Override
            public void cut() {} // Disable cut
            
            @Override
            public void copy() {} // Disable copy
            
            @Override
            public boolean isFocusable() {
                return false; // Prevent focus to disable caret
            }
        };
        
        // Disable drag and drop
        textBox.setDragEnabled(false);
        textBox.setTransferHandler(null);
        
        // Remove all key bindings
        InputMap inputMap = textBox.getInputMap();
        inputMap.clear();
        ActionMap actionMap = textBox.getActionMap();
        actionMap.clear();
        
        // Disable right-click menu
        textBox.setComponentPopupMenu(null);
        
        textBox.setEditable(false);
        textBox.setBackground(Color.BLACK);
        textBox.setForeground(Color.WHITE);
        textBox.setBorder(null);
        textBox.setHighlighter(null); // Disable text highlighting
        
        
        // Custom caret that is invisible
        textBox.setCaret(new DefaultCaret() {
            @Override
            public void paint(Graphics g) {} // Don't paint the caret
        });
        
        textBox.setBorder(BorderFactory.createEmptyBorder(
            (int)(height * 0.15),
            50,
            0,
            50
        ));
        
        StyledDocument doc = textBox.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textAttributes, true);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(Color.BLACK);
        textPanel.add(textBox, BorderLayout.NORTH);
        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(Color.BLACK);
        textPanel.add(spacerPanel, BorderLayout.CENTER);

        // Disable text selection in the entire panel
        textPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleClick();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // Consume the event to prevent text selection
                e.consume();
            }
        });

        storyDialogue.getContentPane().setBackground(Color.BLACK);
        storyDialogue.add(textPanel, BorderLayout.CENTER);
        storyDialogue.setLocation(x, y);

        // SKIP BUTTON
        ImageIcon skipButtonIcon = scaleImageIcon("src/button_assets/skipButton0.png");
        ImageIcon skipButtonHoverIcon = scaleImageIcon("src/button_assets/skipButton1.png");

        JButton skipButton = new JButton(skipButtonIcon);
        int buttonWidth = (int) (screenSize.width * 0.15);
        int buttonHeight = (int) (screenSize.height * 0.15);
        skipButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        skipButton.setBackground(Color.BLACK);
        skipButton.setFocusPainted(false);
        skipButton.setBorderPainted(false);
        skipButton.setContentAreaFilled(false);

        skipButton.addActionListener(e -> {
            storyDialogue.dispose();
            sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
        });

        skipButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                skipButton.setIcon(skipButtonHoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                skipButton.setIcon(skipButtonIcon);
            }
        });

        JPanel skipButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        skipButtonPanel.setBackground(Color.BLACK);
        skipButtonPanel.add(skipButton);

        storyDialogue.add(skipButtonPanel, BorderLayout.NORTH);

        this.size = story.getSize();

        if (size > 0) {
            typewriterEffect(story.getLine(i));
            i++;
        }

        storyDialogue.add(pressToContinueLabel, BorderLayout.SOUTH);
        pressToContinueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }
        });

        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);
    }

    private void typewriterEffect(String text) {
        String plainText = text.replaceAll("<[^>]*>", "");
        currentFullText = plainText;
        
        if (typewriterThread != null && typewriterThread.isAlive()) {
            isTyping = false;
            try {
                typewriterThread.join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        isTyping = true;
        typewriterThread = new Thread(() -> {
            try {
                StringBuilder displayText = new StringBuilder();
                StyledDocument doc = textBox.getStyledDocument();
                
                SwingUtilities.invokeLater(() -> {
                    try {
                        doc.remove(0, doc.getLength());
                        doc.setParagraphAttributes(0, doc.getLength(), textAttributes, true);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                });
                
                for (char c : plainText.toCharArray()) {
                    if (!isTyping) {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                doc.remove(0, doc.getLength());
                                doc.insertString(0, currentFullText, null);
                                doc.setParagraphAttributes(0, doc.getLength(), textAttributes, true);
                            } catch (BadLocationException e) {
                                e.printStackTrace();
                            }
                        });
                        return;
                    }
                    
                    displayText.append(c);
                    final String currentText = displayText.toString();
                    
                    SwingUtilities.invokeLater(() -> {
                        try {
                            doc.remove(0, doc.getLength());
                            doc.insertString(0, currentText, null);
                            doc.setParagraphAttributes(0, doc.getLength(), textAttributes, true);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    });
                    
                    Thread.sleep(30);
                }
                
                isTyping = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        typewriterThread.start();
    }

    private void handleClick() {
        if (isTyping) {
            isTyping = false;
            SwingUtilities.invokeLater(() -> {
                try {
                    StyledDocument doc = textBox.getStyledDocument();
                    doc.remove(0, doc.getLength());
                    doc.insertString(0, currentFullText, null);
                    doc.setParagraphAttributes(0, doc.getLength(), textAttributes, true);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            });
        } else if (i < size) {
            this.index++;
            if (index < size) {
                typewriterEffect(story.getLine(index));
                if (ID == 0){
                    switch (index){
                        case 2:
                           sfxPlayer.playSFX("src/audio_assets/sfx/exposition/parkinglot.wav");
                            break;
                        case 5:
                            sfxPlayer.stopSFX();
                            sfxPlayer.playSFX("src/audio_assets/sfx/exposition/drift.wav");
                            break;
                        case 7:
                            sfxPlayer.stopSFX();
                            sfxPlayer.playSFX("src/audio_assets/sfx/exposition/thunder.wav");
                            break;
                        case 10:
                            sfxPlayer.stopSFX();
                            sfxPlayer.playSFX("src/audio_assets/sfx/exposition/panting.wav");
                            break;
                        case 12:
                            sfxPlayer.stopSFX();
                            sfxPlayer.playSFX("src/audio_assets/sfx/exposition/appear.wav");
                            break;
                        case 14:
                            sfxPlayer.stopSFX();
                            sfxPlayer.playSFX("src/audio_assets/sfx/exposition/panting.wav");
                            break;
                        case 33:
                            sfxPlayer.stopSFX();
                            sfxPlayer.playSFX("src/audio_assets/sfx/exposition/appear.wav");
                            break;
                        default:
                            break;
                    }
                }
            } else {
                storyDialogue.dispose();
            }
        }
    }

    public ImageIcon scaleImageIcon(String path) {
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}

