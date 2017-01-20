/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgj6hbequalizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author chrisjansson
 */
public class EqualizerModel extends Cgj6hbEqualizer {
        
    public void savePreset(ArrayList settings) {            
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Preset");
        File file = fileChooser.showSaveDialog(super.stage);
        
        if (file != null) {
            try {
                FileWriter fileWriter = null;
                fileWriter = new FileWriter(file);
                
                for (int i = 0; i < 10; i++) {//scroll through each band, record value
                    fileWriter.write(settings.get(i).toString());
                    fileWriter.write("\n");
                }
                
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(EqualizerModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void loadPreset(ArrayList settings) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Preset");
        File file = fileChooser.showOpenDialog(super.stage);
        
        if (file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                Double tmp;
                
                for (int i = 0; i < 10; i++) {
                    line = reader.readLine();
                    tmp = Double.parseDouble(line);//convert from string to Double
                    settings.set(i, tmp);//update ArrayList
                }
                
                reader.close();
            } catch (IOException e) {
                System.exit(3); 
            }
        }
    }
}
