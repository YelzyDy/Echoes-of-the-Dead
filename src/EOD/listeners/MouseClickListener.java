/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import EOD.gameInterfaces.MouseInteractable;

/**
 *
 * @author Joana
 */
public class MouseClickListener extends MouseAdapter{
    private MouseInteractable interactable;

    public MouseClickListener(MouseInteractable interactable) {
        this.interactable = interactable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (interactable != null) {
            interactable.onClick(e);
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
