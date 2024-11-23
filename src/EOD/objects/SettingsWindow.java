package EOD.objects;

import EOD.utils.BGMPlayer;
import EOD.utils.SFXPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsWindow extends JFrame {
    private JRadioButton musicOnButton;
    private JRadioButton musicOffButton;
    private JRadioButton sfxOnButton;
    private JRadioButton sfxOffButton;
    private JSlider volumeSlider;
    private JLabel volumeLabel;
    private BGMPlayer bgmPlayer;
    private SFXPlayer sfxPlayer;
    private static SettingsWindow instance = null;

    private SettingsWindow(BGMPlayer bgmPlayer, SFXPlayer sfxPlayer) {
        this.bgmPlayer = bgmPlayer;
        this.sfxPlayer = sfxPlayer;
        this.setTitle("Settings");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(7, 1));  // Adjusted for proper spacing

        // Music toggle section
        JLabel musicLabel = new JLabel("Enable or disable sound:");
        musicLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create radio buttons
        musicOnButton = new JRadioButton("Music On", bgmPlayer.getIsMusicEnabled());
        musicOffButton = new JRadioButton("Music Off", !bgmPlayer.getIsMusicEnabled());
        sfxOnButton = new JRadioButton("SFX On", sfxPlayer.getIsSFXEnabled());
        sfxOffButton = new JRadioButton("SFX Off", !sfxPlayer.getIsSFXEnabled());

        // Group the radio buttons
        ButtonGroup musicGroup = new ButtonGroup();
        musicGroup.add(musicOnButton);
        musicGroup.add(musicOffButton);

        ButtonGroup sfxGroup = new ButtonGroup();
        sfxGroup.add(sfxOnButton);
        sfxGroup.add(sfxOffButton);

        // Add action listeners to the buttons
        musicOnButton.addActionListener(new MusicToggleListener());
        musicOffButton.addActionListener(new MusicToggleListener());
        sfxOnButton.addActionListener(new SfxToggleListener());
        sfxOffButton.addActionListener(new SfxToggleListener());

        // Volume control section
        JPanel volumePanel = new JPanel(new FlowLayout());
        float currentVolume = sfxPlayer.getCurrentVolume();
        int sliderValue = (int)(currentVolume * 100);
        volumeLabel = new JLabel("Volume: " + sliderValue + "%");
        
        // Create volume slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, sliderValue);
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(new VolumeChangeListener());

        volumePanel.add(volumeLabel);
        volumePanel.add(volumeSlider);

        // Add all components to the frame
        this.add(musicLabel);
        JPanel musicPanel = new JPanel(new FlowLayout());
        musicPanel.add(musicOnButton);
        musicPanel.add(musicOffButton);
        this.add(musicPanel);

        JPanel sfxPanel = new JPanel(new FlowLayout());
        sfxPanel.add(sfxOnButton);
        sfxPanel.add(sfxOffButton);
        this.add(sfxPanel);

        this.add(new JLabel("Volume Control:", JLabel.CENTER));
        this.add(volumePanel);

        // Pack the frame to ensure proper layout
        this.pack();
        this.setSize(400, 300); // Set final size after packing
    }

    public static SettingsWindow getInstance(BGMPlayer bgmPlayer, SFXPlayer sfxPlayer) {
        if (instance == null || instance.bgmPlayer == null || instance.sfxPlayer == null) {
            instance = new SettingsWindow(bgmPlayer, sfxPlayer);
        }
        return instance;
    }

    private class MusicToggleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (musicOnButton.isSelected()) {
                bgmPlayer.setMusicEnabled(true);
                if (bgmPlayer.getFilePath() != null) {
                    bgmPlayer.playBGM(bgmPlayer.getFilePath());
                }
            } else if (musicOffButton.isSelected()) {
                bgmPlayer.setMusicEnabled(false);
                bgmPlayer.stopBGM();
            }
        }
    }

    private class SfxToggleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (sfxOnButton.isSelected()) {
                sfxPlayer.setSFXEnabled(true);
            } else if (sfxOffButton.isSelected()) {
                sfxPlayer.setSFXEnabled(false);
                sfxPlayer.stopAllSFX();
            }
        }
    }

    private class VolumeChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int value = volumeSlider.getValue();
            volumeLabel.setText("Volume: " + value + "%");
            
            float normalizedVolume = value / 100.0f;
            bgmPlayer.setVolume(normalizedVolume);
            sfxPlayer.setVolume(normalizedVolume);
        }
    }
}