
package EOD.utils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class BGMPlayer {
    private Clip clip;

    public void playBGM(String bgmFilePath) {
        try {
            // Load the sound file as an audio stream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(bgmFilePath));
            
            // Get a sound clip resource
            clip = AudioSystem.getClip();
            
            // Open the audio stream and load it into the clip
            clip.open(audioStream);
            
            // Loop the music indefinitely (loop continuously)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
            // Start playing the music
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBGM() {
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Stop the BGM
        }
    }
}
