package EOD.objects;

import EOD.utils.BGMPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsWindow extends JFrame {
    private JRadioButton musicOnButton;
    private JRadioButton musicOffButton;
    private JSlider volumeSlider;
    private JLabel volumeLabel;
    private BGMPlayer bgmPlayer;
    private static SettingsWindow instance = null;
    private SettingsWindow(BGMPlayer bgmPlayer) {
        
        this.setTitle("Settings");
        this.setSize(400, 300);  // Increased height to accommodate slider
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(5, 1));  // Increased rows for new components

        // Music toggle section
        JLabel musicLabel = new JLabel("Enable or disable background music:");
        musicLabel.setHorizontalAlignment(JLabel.CENTER);

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

        // Volume control section
        JPanel volumePanel = new JPanel(new FlowLayout());
        volumeLabel = new JLabel("Volume: 100%");
        
        // Create volume slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(new VolumeChangeListener());

        volumePanel.add(volumeLabel);
        volumePanel.add(volumeSlider);

        // Add all components to the frame
        this.add(musicLabel);
        this.add(musicOnButton);
        this.add(musicOffButton);
        this.add(new JLabel("Volume Control:")); // Section header
        this.add(volumePanel);
        this.bgmPlayer = bgmPlayer;
    }

    // Singleton pattern: getInstance() to access the single instance
    public static SettingsWindow getInstance(BGMPlayer bgmPlayer) {
        if (instance == null) {
            instance = new SettingsWindow(bgmPlayer);
        }
        return instance;
    }


    // Inner class to handle music toggling
    private class MusicToggleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (musicOnButton.isSelected()) {
                bgmPlayer.setMusicEnabled(true);
                bgmPlayer.playBGM(bgmPlayer.getFilePath());
            } else if (musicOffButton.isSelected()) {
                bgmPlayer.setMusicEnabled(false);
                //bgmPlayer.pauseBGM();  // Pauses the music when turned off
                bgmPlayer.stopBGM();
            }
        }
    }

    // Inner class to handle volume changes
    private class VolumeChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int value = volumeSlider.getValue();
            volumeLabel.setText("Volume: " + value + "%");
            
            // Assuming BGMPlayer has a setVolume method that accepts a value between 0.0 and 1.0
            float normalizedVolume = value / 100.0f;
            bgmPlayer.setVolume(normalizedVolume);
        }
    }
}