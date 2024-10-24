package EOD.objects;

import EOD.utils.BGMPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsWindow extends JFrame {
    private JRadioButton musicOnButton;
    private JRadioButton musicOffButton;
    private BGMPlayer bgmPlayer;

    public SettingsWindow(BGMPlayer bgmPlayer) {
        this.bgmPlayer = bgmPlayer;
        
        this.setTitle("Settings");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close this window without closing the main app
        this.setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Enable or disable background music:");
        label.setHorizontalAlignment(JLabel.CENTER);

        // Create radio buttons
        musicOnButton = new JRadioButton("Music On", true);
        musicOffButton = new JRadioButton("Music Off");

        // Group the radio buttons
        ButtonGroup musicGroup = new ButtonGroup();
        musicGroup.add(musicOnButton);
        musicGroup.add(musicOffButton);

        // Add action listeners to the buttons
        musicOnButton.addActionListener(new MusicToggleListener());
        musicOffButton.addActionListener(new MusicToggleListener());

        this.add(label);
        this.add(musicOnButton);
        this.add(musicOffButton);
    }

    // Inner class to handle music toggling
    private class MusicToggleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (musicOnButton.isSelected()) {
                bgmPlayer.playBGM("src/audio_assets/selection.wav");  // Play music
            } else if (musicOffButton.isSelected()) {
                bgmPlayer.stopBGM();  // Stop music
            }
        }
    }
}
