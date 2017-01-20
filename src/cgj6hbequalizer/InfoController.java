/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgj6hbequalizer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author chrisjansson
 */
public class InfoController extends Switchable implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleBack(Event event) {
        Switchable.switchTo("FXMLEqualizer");
 
        FXMLEqualizerController controller = 
                (FXMLEqualizerController)getControllerByName("FXMLEqualizer");
    }
}
