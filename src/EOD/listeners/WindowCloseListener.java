package EOD.listeners;

import EOD.gameInterfaces.WindowInteractable;
import java.awt.event.*;

public class WindowCloseListener extends WindowAdapter{
    private WindowInteractable interactable;
    //private SFXPlayer sfxPlayer;
    public WindowCloseListener(WindowInteractable interactable) {
        this.interactable = interactable;
    }

    public void onWindowClosing(WindowEvent e){
        interactable.onWindowClosing(e);
    }
}
