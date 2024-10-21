package EOD.dialogues;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AskDialogues extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    StoryLine story = new StoryLine();
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.44);
    private int size;

    public void openScrollableDialog(int ID, JDialog dialog, JLabel textBox) {

        // LOAD NPC

        switch (ID) {
            case 2:
                story.missConstanceLines();
                break;
            case 4:
                story.nattyLines();
                break;
            case 6:
                story.yooLines();
                break;
            case 8:
                story.migginsLines();
                break;
            case 10:
                story.faithfulLines();
                break;
            default:
                break;
        }

        dialog.getContentPane().setBackground(Color.BLACK);
        dialog.add(textBox, BorderLayout.CENTER);

        // SCROLL PANEL

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 0;

        // SCROLL WINDOW & PANE

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
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

        this.size = story.getSize();
        String[] options = new String[this.size];
        options = story.getArr();

        for (int i = 0; i < options.length; i++) {
            if (options[i] != null && i % 2 == 0) {
                int j = i;
                JButton optionButton = new JButton("<html><center>" + options[i] + "</center></html>");
                optionButton.setFont(new Font("Monospaced", Font.PLAIN, 28));
                optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                optionButton.setForeground(Color.WHITE);
                optionButton.setBackground(Color.BLACK);
                optionButton.setFocusPainted(false);
                optionButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

                int preferredHeight = optionButton.getPreferredSize().height * 2;
                optionButton.setPreferredSize(new Dimension(300, preferredHeight));
                optionButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, preferredHeight));

                optionButton.addActionListener(e -> {
                    textBox.setText(story.getLine(j + 1));
                    dialog.remove(scrollPane);
                    dialog.revalidate();
                    dialog.repaint();
                });

                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.BOTH;
                buttonPanel.add(optionButton, gbc);
            }
        }

        // SCROLL PANE EVENT LISTENER

        dialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                dialog.dispose();
            }
        });

        // DISPLAY

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.getContentPane().setBackground(Color.BLACK);
        dialog.setLocation(x, y);
        dialog.setVisible(true);
    }
}
