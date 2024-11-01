package EOD.gameInterfaces;

import EOD.animator.Animator;
import EOD.scenes.SceneBuilder;

public interface CharacterBlueprint extends Entity, SGWorld{
    public void setAnimator(Animator animator);

    public Animator getAnimator();

    public String getCharacterType();

    public void setpanel(SceneBuilder panel);

    public SceneBuilder getPanel();

    public void setCharacterType(String characterType);
}
