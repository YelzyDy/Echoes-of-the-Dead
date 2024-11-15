package EOD.utils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SFXPlayer {
    private static SFXPlayer instance = null;
    private Clip clip;
    private FloatControl gainControl;
    private float currentVolume = 1.0f; // Default volume 
    private boolean isSFXEnabled = true;
    private String filepath = null;

    // Private constructor to prevent multiple instances
    private SFXPlayer() {}

    // Static method to get the single instance of BGMPlayer
    public static SFXPlayer getInstance() {
        if (instance == null) {
            instance = new SFXPlayer();
        }
        return instance;
    }


    public synchronized void playSFX(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("Error: filePath is null or empty");
            return;
        }
        this.filepath = filePath;
        if (!isSFXEnabled) {
            return;
        }
    
        new Thread(() -> {
            try {
                if (clip != null && clip.isRunning()) {
                    stopSFX();
                }
    
                File audioFile = new File(filePath);
                if (!audioFile.exists()) {
                    System.err.println("Audio file not found: " + filePath);
                    return;
                }
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
    
                if (!AudioSystem.isLineSupported(info)) {
                    throw new UnsupportedOperationException("Audio format not supported");
                }
    
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioStream);
    
                gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(currentVolume);
    
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    

    public void stopSFX() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null; // Set to null to avoid any further null reference issues
        } else {
            System.out.println("null sfx");
        }
        filepath = null;
    }
    

    public void setVolume(float volume) {
        currentVolume = volume;
        if (gainControl == null) {
            return;
        }
    
        try {
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            float adjustedVolume = (volume < 0.0001f) ? 0.0001f : volume;
            float dB = (float) (Math.log10(adjustedVolume) * 20.0f);
            dB = Math.max(min, Math.min(max, dB));
            gainControl.setValue(dB);
    
            System.out.println("Volume set to: " + dB);  // Debugging output
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public float getCurrentVolume() {
        return currentVolume;
    }

    // Check if BGM is currently playing
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    public void setSFXEnabled(boolean enabled) {
        isSFXEnabled = enabled;
        if (!enabled) {
            stopSFX(); // Stop playing music if it is disabled
        }
    }

    public boolean getIsSFXEnabled() {
        return isSFXEnabled;
    }

    public String getFilePath(){
        return filepath;
    }
}