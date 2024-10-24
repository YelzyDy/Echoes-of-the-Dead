package EOD.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGMPlayer {
    private Clip clip;
    private FloatControl gainControl;
    private float currentVolume = 0.5f; // Default volume at 50%
    private String currentBGMPath = null;

    public void playBGM(String filePath) {
        try {
            // If the same BGM is already playing, don't restart it
            if (currentBGMPath != null && currentBGMPath.equals(filePath) && 
                clip != null && clip.isRunning()) {
                return;
            }

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

    public float getCurrentVolume() {
        return currentVolume;
    }

    // Pause the current BGM
    public void pauseBGM() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Resume the current BGM
    public void resumeBGM() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    // Check if BGM is currently playing
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}