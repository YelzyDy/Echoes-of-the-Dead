package EOD.utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sound.sampled.*;

public class SFXPlayer {
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();
    private static SFXPlayer instance = null;
    private ConcurrentHashMap<String, Clip> activeClips = new ConcurrentHashMap<>();
    private float currentVolume = 1.0f;
    private boolean isSFXEnabled = true;

    // Private constructor to prevent multiple instances
    private SFXPlayer() {}

    // Static method to get the single instance of SFXPlayer
    public static SFXPlayer getInstance() {
        if (instance == null) {
            instance = new SFXPlayer();
        }
        return instance;
    }

    public synchronized void playSFX(String filePath) {
        if (!isSFXEnabled || filePath == null || filePath.isEmpty()) {
            return;
        }

        threadPool.submit(() -> {
            try {
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

                Clip newClip = (Clip) AudioSystem.getLine(info);
                newClip.open(audioStream);

                // Set the volume for the new clip
                if (newClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl gainControl = (FloatControl) newClip.getControl(FloatControl.Type.MASTER_GAIN);
                    setVolumeForControl(gainControl, currentVolume);
                }

                // Add listener to remove clip from active clips when it's done
                newClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        newClip.close();
                        activeClips.remove(filePath + System.nanoTime());
                    }
                });

                // Store the clip with a unique key (filepath + timestamp)
                String uniqueKey = filePath + System.nanoTime();
                activeClips.put(uniqueKey, newClip);
                newClip.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
    }

    public void stopAllSFX() {
        activeClips.forEach((key, clip) -> {
            clip.stop();
            clip.close();
        });
        activeClips.clear();
    }

    public void setVolume(float volume) {
        currentVolume = volume;
        // Update volume for all active clips
        activeClips.forEach((key, clip) -> {
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolumeForControl(gainControl, volume);
            }
        });
    }

    private void setVolumeForControl(FloatControl gainControl, float volume) {
        try {
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            float adjustedVolume = Math.max(0.0001f, volume);
            float dB = (float) (Math.log10(adjustedVolume) * 20.0f);
            dB = Math.max(min, Math.min(max, dB));
            gainControl.setValue(dB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getCurrentVolume() {
        return currentVolume;
    }

    public boolean isAnyPlaying() {
        return !activeClips.isEmpty();
    }

    public void setSFXEnabled(boolean enabled) {
        isSFXEnabled = enabled;
        if (!enabled) {
            stopAllSFX();
        }
    }

    public boolean getIsSFXEnabled() {
        return isSFXEnabled;
    }

    // Cleanup method to be called when shutting down
    public void cleanup() {
        stopAllSFX();
        threadPool.shutdown();
    }
}