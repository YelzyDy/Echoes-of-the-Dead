package EOD.utils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class BGMPlayer {
    private static BGMPlayer instance = null;
    private Clip clip;
    private FloatControl gainControl;
    private float currentVolume = 1.0f; // Default volume at 50%
    private String currentBGMPath = null;
    private boolean isMusicEnabled = true;
    private String filepath;

    // CONSTRUCTORS
    // Private constructor to prevent multiple instances
    private BGMPlayer() {}

    // Static method to get the single instance of BGMPlayer
    public static BGMPlayer getInstance() {
        if (instance == null) {
            instance = new BGMPlayer();
        }
        return instance;
    }

    // SETTERS 
    public void setVolume(float volume) {
        try {
            currentVolume = volume; // Store the current volume level
            
            if (gainControl != null) {
                // Convert linear scale (0.0 to 1.0) to dB scale (-80.0 to 6.0 dB)
                float min = gainControl.getMinimum();
                float max = gainControl.getMaximum();
                
                // Avoid log(0) by using a small value when volume is 0
                float adjustedVolume = (volume < 0.0001f) ? 0.0001f : volume;
                
                // Convert to dB
                float dB = (float) (Math.log10(adjustedVolume) * 20.0f);
                
                // Ensure the value is within valid range
                dB = Math.max(min, Math.min(max, dB));
                
                gainControl.setValue(dB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMusicEnabled(boolean enabled) {
        isMusicEnabled = enabled;
        if (!enabled) {
            stopBGM(); // Stop playing music if it is disabled
        }
    }



    // GETTERS 
    public float getCurrentVolume() {
        return currentVolume;
    }

    public boolean getIsMusicEnabled() {
        return isMusicEnabled;
    }

    public String getFilePath(){
        return filepath;
    }



    // LOCAL METHODS
    public void playBGM(String filePath) {
        this.filepath = filePath;
        if (!isMusicEnabled){
            return;
        } 
    
        try {
            // Stop any currently playing BGM
            stopBGM();
    
            // Load and play the new BGM
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
    
            if (!AudioSystem.isLineSupported(info)) {
                throw new UnsupportedOperationException("Audio format not supported");
            }
    
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
    
            // Get the gain control
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    
            // Apply the current volume setting
            setVolume(currentVolume);
    
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            currentBGMPath = filePath;
    
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    

    public void stopBGM() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
        currentBGMPath = null;
    }    

    // Pause the current BGM
    public void pauseBGM() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Resume the current BGM
    public void resumeBGM() {
        if (currentBGMPath != null) {
            try {
                // If clip is not running, restart it from the beginning
                if (!clip.isRunning()) {
                    clip.setFramePosition(0); // Set the frame position to the beginning
                    clip.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Check if BGM is currently playing
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    // OVERRIDDEN METHODS - NONE

    // ABSTRACT METHODS - NONE
}