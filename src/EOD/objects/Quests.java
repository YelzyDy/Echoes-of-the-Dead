package EOD.objects;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class Quests extends JPanel {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private int ifActive = 5;

    private final JPanel textPanel;
    private JScrollPane scrollPane;
    private JList<String> textList;
    private DefaultListModel<String> textListModel;

    public Quests() {
        this.textPanel = new JPanel();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBounds(0, (int) (screenSize.height * 0.4),
                (int) (screenSize.width), (int) (screenSize.height * 0.45));

        initializeQuestLabel();
        initializeTextPanel();
        initializeTextList();

        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    private void initializeQuestLabel() {
        JLabel questLabel = new JLabel(" Quests:");
        questLabel.setFont(new Font("Monospaced", Font.PLAIN, 36));
        questLabel.setForeground(Color.WHITE);
        questLabel.setHorizontalAlignment(SwingConstants.LEFT);

        add(questLabel, BorderLayout.NORTH);
    }

    private void initializeTextPanel() {
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.BLACK);
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(textPanel, BorderLayout.CENTER);
    }

    private void initializeTextList() {
        textListModel = new DefaultListModel<>();
        textList = new JList<>(textListModel);
        textList.setBackground(Color.BLACK);
        textList.setForeground(Color.WHITE);
        textList.setFont(new Font("Monospaced", Font.PLAIN, 28));
        textList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add quests based on ifActive
        for (int i = ifActive; i >= 0; i--) {
            String questText = getQuestTextForIndex(i);

            if (i == ifActive) {
                textListModel.addElement(questText);
            } else {
                textListModel.addElement("<html><font color='#808080'>" + questText + "</font></html>");
            }
        }

        textList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
                label.setHorizontalAlignment(SwingConstants.CENTER);
        
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
        
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
        
                label.setPreferredSize(new Dimension(label.getPreferredSize().width, 85));
        
                return label;
            }
        });

        textList.setLayoutOrientation(JList.VERTICAL);

        scrollPane = new JScrollPane(textList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        textPanel.add(scrollPane);
    }

    private String getQuestTextForIndex(int index) { // Change the quests here
        switch (index) {
            case 13: return "Explore the ruins.";
            case 12: return "Purchase potions.";
            case 11: return "Enter the red portal.";
            case 10: return "Defeat the Necromancer.";
            case 9: return "Explore the city outskirts.";
            case 8: return "Solve the riddle of the old reaper.";
            case 7: return "Rescue Miggins.";
            case 6: return "Investigate the shop.";
            case 5: return "Help the locals.";
            case 4: return "Retrieve the ancient artifact.";
            case 3: return "Defeat the skeleton.";
            case 2: return "Find the green portal.";
            case 1: return "Talk to everyone.";
            case 0: return "Look around.";
            default: return "Welcome!";
        }
    }

    public void setQuestStatus(int ifActive) {
        this.ifActive = ifActive;
    }
}
