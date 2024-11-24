package EOD.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import EOD.characters.Player;
import java.awt.Image;

import EOD.gameInterfaces.Entity;
import EOD.scenes.SceneBuilder;

public class SkillEffects extends EchoesObjects {
    private boolean isLooping;
    private int stopFrame;
    private boolean isActive;
    private Entity target;
    private double offsetX;
    private double offsetY;
    private SceneBuilder panel;
    private int loopStartFrame; 
    
    public SkillEffects(String assetPackage, int x, int y, int width, int height, 
                      String type, int numOfSprites, SceneBuilder panel) {
        super(assetPackage, x, y, width, height, type, true, false, numOfSprites);
        this.isLooping = true;
        this.stopFrame = numOfSprites - 1;
        this.isActive = false;
        this.panel = panel;
        this.loopStartFrame = 0; 
        setOpaque(false);
        setVisible(true);
    }

    @Override
    public void free(){
        panel.free();
        panel = null;
    }
    
    public void updateEffect(Player player) {
        if (!isActive) return;
        // Assuming 'panel' is the parent container of your components

        panel.setComponentZOrder(this, 1);
        panel.setComponentZOrder(player, 2);
        updateAnimation();
        
        if (target != null) {
            double newX = target.getPosX() + offsetX;
            double newY = target.getPosY() + offsetY;
            setPosX(newX);
            setPosY(newY);
            setBounds((int)newX, (int)newY, getWidth(), getHeight());
        }
        
        // Check if we've reached the stop frame
        if (!isLooping && getCurrentFrame() >= stopFrame) {
            stop();  // This will hide and remove the effect
            return;  // Exit early to prevent further updates
        }
        
        // Only repaint if the effect is still active
        if (isActive) {
            repaint();
        }
    }

    @Override
    public void updateAnimation() {
        if (!isActive) return;
        
        setCurrentFrame(getCurrentFrame() + 1);
        
        // If we're looping, reset to the loop start frame when we reach the end
        if (isLooping && getCurrentFrame() >= getNumOfSprites()) {
            setCurrentFrame(loopStartFrame);
        }
        // If we're not looping, ensure we don't exceed the number of sprites
        else if (!isLooping) {
            if (getCurrentFrame() >= getNumOfSprites()) {
                setCurrentFrame(getNumOfSprites() - 1);
            }
        }
    }

    /**
     * Sets the frame to start looping from after completing the full animation once.
     * @param startFrame The frame index to start looping from (0-based index)
     * @throws IllegalArgumentException if startFrame is negative or >= number of sprites
     */
    public void setLoopStartFrame(int startFrame) {
        if (startFrame < 0 || startFrame >= getNumOfSprites()) {
            throw new IllegalArgumentException("Loop start frame must be between 0 and " + (getNumOfSprites() - 1));
        }
        this.loopStartFrame = startFrame;
    }

    /**
     * Gets the current loop start frame
     * @return The frame index where looping begins
     */
    public int getLoopStartFrame() {
        return loopStartFrame;
    }
    
    public void setLooping(boolean isLooping) {
        this.isLooping = isLooping;
    }
    
    public void setStopFrame(int frame) {
        this.stopFrame = Math.min(frame, getNumOfSprites() - 1);
    }

    public void bindToTarget(Entity target, double offsetX, double offsetY) {
        this.target = target;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        // updateEffect(); // Immediately update position
    }

    public void unbindTarget() {
        this.target = null;
    }
      
    public void play() {
        if (isActive) return;
        
        isActive = true;
        setVisible(true);
        
        if (!panel.isAncestorOf(this)) {
            panel.setLayout(null);
            panel.add(this);
        }
        
        // Check if we have a bound target and update position immediately
        if (target != null) {
            double newX = target.getPosX() + offsetX;
            double newY = target.getPosY() + offsetY;
            setPosX(newX);
            setPosY(newY);
        }
        
        // Now set bounds using the potentially updated position
        setBounds((int)getPosX(), (int)getPosY(), getWidth(), getHeight());
        
        restartAnimation();
        panel.revalidate();
        panel.repaint();
    }
    
    public void stop() {
        isActive = false;
        setVisible(false);
        setCurrentFrame(0);  // Reset the frame counter
        
        if (panel.isAncestorOf(this)) {
            panel.remove(this);
            panel.revalidate();
            panel.repaint();
        }
        
        // Clean up any bindings
        unbindTarget();
    }
    
    public void pause() {
        isActive = false;
    }
    
    public void resume() {
        isActive = true;
    }
    
    public boolean isActive() {
        return isActive;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isActive || !isVisible()) return;
        
        // Don't call super.paintComponent() since we want full control over rendering
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        // Draw relative to component bounds
        Image currentSprite = getCurrentSprite();
        if (currentSprite != null) {
            g2d.drawImage(currentSprite, 0, 0, getWidth(), getHeight(), null);
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if (!isActive || !isVisible()) return;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(getCurrentSprite(), (int)getPosX(), (int)getPosY(), null);
    }
    
    // Clean up resources when no longer needed
    public void dispose() {
        stop();
    }
}