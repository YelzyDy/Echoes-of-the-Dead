package EOD.gameInterfaces;

public interface SkillEffectsBlueprint {
    public void updateEffect();

    public void setLoopStartFrame(int startFrame);
    
    public int getLoopStartFrame();
    
    public void setLooping(boolean isLooping);
    
    public void setStopFrame(int frame);

    public void bindToTarget(Entity target, double offsetX, double offsetY);

    public void unbindTarget();
      
    public void play();
    
    public void stop();
    
    public void pause();
    
    public void resume();
    
    public boolean isActive();
    
    public void dispose();
}
