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
    private JSlider musicVolumeSlider;
    private JSlider sfxVolumeSlider;
    private JLabel musicVolumeLabel;
    private JLabel sfxVolumeLabel;
    private BGMPlayer bgmPlayer;
    private SFXPlayer sfxPlayer;
    private static SettingsWindow instance = null;

    private SettingsWindow(BGMPlayer bgmPlayer, SFXPlayer sfxPlayer) {
        this.bgmPlayer = bgmPlayer;
        this.sfxPlayer = sfxPlayer;
        this.setTitle("Settings");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Use BoxLayout for better vertical spacing control
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Sound toggle section
        JLabel soundLabel = new JLabel("Enable or disable sound:");
        soundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
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

        // Add action listeners
        musicOnButton.addActionListener(new MusicToggleListener());
        musicOffButton.addActionListener(new MusicToggleListener());
        sfxOnButton.addActionListener(new SfxToggleListener());
        sfxOffButton.addActionListener(new SfxToggleListener());

        // Create panels for radio buttons with FlowLayout
        JPanel musicButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        musicButtonPanel.add(musicOnButton);
        musicButtonPanel.add(musicOffButton);

        JPanel sfxButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sfxButtonPanel.add(sfxOnButton);
        sfxButtonPanel.add(sfxOffButton);

        // Music volume section
        JLabel musicControlLabel = new JLabel("Music Volume Control:");
        musicControlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create music volume panel with label and slider
        JPanel musicVolumePanel = new JPanel(new BorderLayout(10, 0));
        float currentMusicVolume = bgmPlayer.getCurrentVolume();
        int musicSliderValue = (int)(currentMusicVolume * 100);
        musicVolumeLabel = new JLabel("Music Volume: " + musicSliderValue + "%");
        
        musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, musicSliderValue);
        musicVolumeSlider.setMajorTickSpacing(20);
        musicVolumeSlider.setMinorTickSpacing(5);
        musicVolumeSlider.setPaintTicks(true);
        musicVolumeSlider.setPaintLabels(true);
        musicVolumeSlider.addChangeListener(new MusicVolumeChangeListener());

        musicVolumePanel.add(musicVolumeLabel, BorderLayout.WEST);
        musicVolumePanel.add(musicVolumeSlider, BorderLayout.CENTER);

        // SFX volume section
        JLabel sfxControlLabel = new JLabel("SFX Volume Control:");
        sfxControlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create SFX volume panel with label and slider
        JPanel sfxVolumePanel = new JPanel(new BorderLayout(10, 0));
        float currentSFXVolume = sfxPlayer.getCurrentVolume();
        int sfxSliderValue = (int)(currentSFXVolume * 100);
        sfxVolumeLabel = new JLabel("SFX Volume: " + sfxSliderValue + "%");
        
        sfxVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, sfxSliderValue);
        sfxVolumeSlider.setMajorTickSpacing(20);
        sfxVolumeSlider.setMinorTickSpacing(5);
        sfxVolumeSlider.setPaintTicks(true);
        sfxVolumeSlider.setPaintLabels(true);
        sfxVolumeSlider.addChangeListener(new SFXVolumeChangeListener());

        sfxVolumePanel.add(sfxVolumeLabel, BorderLayout.WEST);
        sfxVolumePanel.add(sfxVolumeSlider, BorderLayout.CENTER);

        // Add components to main panel with spacing
        mainPanel.add(soundLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(musicButtonPanel);
        mainPanel.add(sfxButtonPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(musicControlLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(musicVolumePanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(sfxControlLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(sfxVolumePanel);

        // Add main panel to frame
        this.add(mainPanel);
        this.setResizable(false);
        this.pack();
        this.setSize(400, 400);
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

    private class MusicVolumeChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int value = musicVolumeSlider.getValue();
            musicVolumeLabel.setText("Music Volume: " + value + "%");
            float normalizedVolume = value / 100.0f;
            bgmPlayer.setVolume(normalizedVolume);
        }
    }

    private class SFXVolumeChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int value = sfxVolumeSlider.getValue();
            sfxVolumeLabel.setText("SFX Volume: " + value + "%");
            float normalizedVolume = value / 100.0f;
            sfxPlayer.setVolume(normalizedVolume);
        }
    }
}