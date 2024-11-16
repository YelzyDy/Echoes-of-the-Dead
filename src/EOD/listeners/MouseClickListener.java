/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.listeners;

import EOD.gameInterfaces.MouseInteractable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Joana
 */
public class MouseClickListener extends MouseAdapter{
    private MouseInteractable interactable;
    //private SFXPlayer sfxPlayer;
    public MouseClickListener(MouseInteractable interactable) {
        this.interactable = interactable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (interactable != null) {
            interactable.onClick(e);
            //sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (interactable != null) {
            interactable.onHover(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (interactable != null) {
            interactable.onExit(e);
        }
    }
}