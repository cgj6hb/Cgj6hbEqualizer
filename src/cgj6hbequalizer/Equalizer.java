/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgj6hbequalizer;

import javafx.event.ActionEvent;
import javafx.event.Event;

/**
 *
 * @author chrisjansson
 */
public interface Equalizer {
    public void handleOpen(Event event);
    public void handleSave(Event event);
    public void handleLoad(Event event);
    public void handleAbout(Event event);
    public void handlePlay(ActionEvent event);
    public void handlePause(ActionEvent event);
    public void handleStop(ActionEvent event);
    public void handlePowerButton(Event event);
    public void handleSliderReleased(Event event);
}
