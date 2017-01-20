/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgj6hbequalizer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author chrisjansson
 */
public class FXMLEqualizerController extends Switchable implements Initializable, Equalizer {
    EqualizerModel model;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Slider firstSlider;
    @FXML
    private Slider secondSlider;
    @FXML
    private Slider thirdSlider;
    @FXML
    private Slider fourthSlider;
    @FXML
    private Slider fifthSlider;
    @FXML
    private Slider sixthSlider;
    @FXML
    private Slider seventhSlider;
    @FXML
    private Slider eighthSlider;
    @FXML
    private Slider ninthSlider;
    @FXML
    private Slider tenthSlider;
    
    @FXML
    private Label firstLabel;
    @FXML
    private Label secondLabel;
    @FXML
    private Label thirdLabel;
    @FXML
    private Label fourthLabel;
    @FXML
    private Label fifthLabel;
    @FXML
    private Label sixthLabel;
    @FXML
    private Label seventhLabel;
    @FXML
    private Label eighthLabel;
    @FXML
    private Label ninthLabel;
    @FXML
    private Label tenthLabel;
    
    private Media media;
    private MediaPlayer mediaPlayer;
    private String tmp;
    
    ObservableList<EqualizerBand> bandList;
    ArrayList<Double> settings = new ArrayList<>();
    AudioEqualizer equalizer;
    
    public FXMLEqualizerController() {
        this.model = new EqualizerModel();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 10; i++) {
            settings.add(i, 0.0);
        }
    }    
    
    public void openMedia(File file) {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

        try {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() -> {
                handleEndOfMedia();
            });
            
            mediaPlayer.setAutoPlay(true); 
        } catch (Exception ex) {
            System.out.println("Unable to open media.");
        }
                  
        try {
            equalizer = mediaPlayer.getAudioEqualizer();
            bandList = equalizer.getBands();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public void handleEndOfMedia() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
    }
    
    @FXML
    @Override
    public void handleOpen(Event event) {
        Stage primaryStage = (Stage)anchorPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            openMedia(file);
        }
    }
    
    @FXML
    @Override
    public void handleSave(Event event) {
        model.savePreset(settings);
    }
    
    @FXML
    @Override
    public void handleLoad(Event event) {
        model.loadPreset(settings);//read in loaded settings
        
        //set all sliders from loaded setting
        bandList.get(0).setGain(settings.get(0));
        firstLabel.setText(settings.get(0).toString() + " dB");
        firstSlider.setValue(settings.get(0));
        
        bandList.get(1).setGain(settings.get(1));
        secondLabel.setText(settings.get(1).toString() + " dB");
        secondSlider.setValue(settings.get(1));
        
        bandList.get(2).setGain(settings.get(2));
        thirdLabel.setText(settings.get(2).toString() + " dB");
        thirdSlider.setValue(settings.get(2));
        
        bandList.get(3).setGain(settings.get(3));
        fourthLabel.setText(settings.get(3).toString() + " dB");
        fourthSlider.setValue(settings.get(3));
        
        bandList.get(4).setGain(settings.get(4));
        fifthLabel.setText(settings.get(4).toString() + " dB");
        fifthSlider.setValue(settings.get(4));
        
        bandList.get(5).setGain(settings.get(5));
        sixthLabel.setText(settings.get(5).toString() + " dB");
        sixthSlider.setValue(settings.get(5));
        
        bandList.get(6).setGain(settings.get(6));
        seventhLabel.setText(settings.get(6).toString() + " dB");
        seventhSlider.setValue(settings.get(6));
        
        bandList.get(7).setGain(settings.get(7));
        eighthLabel.setText(settings.get(7).toString() + " dB");
        eighthSlider.setValue(settings.get(7));
        
        bandList.get(8).setGain(settings.get(8));
        ninthLabel.setText(settings.get(8).toString() + " dB");
        ninthSlider.setValue(settings.get(8));
        
        bandList.get(9).setGain(settings.get(9));
        tenthLabel.setText(settings.get(9).toString() + " dB");
        tenthSlider.setValue(settings.get(9));
    }
    
    @FXML
    @Override
    public void handleAbout(Event event) {
        switchTo("Info");
    }
    
    @FXML
    @Override
    public void handlePlay(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
    
    @FXML
    @Override
    public void handlePause(ActionEvent event) {
        if (mediaPlayer != null) {
           mediaPlayer.pause(); 
        }
    }
    
    @FXML
    @Override
    public void handleStop(ActionEvent event) {
        if (mediaPlayer != null) {
           mediaPlayer.stop();
        }
    }
    
    @FXML
    @Override
    public void handlePowerButton(Event event) {//this bypasses the EQ effect    
        if (equalizer.isEnabled() == true) {
            equalizer.setEnabled(false);
        } else {
            equalizer.setEnabled(true);
        }
    }
    
    @FXML
    @Override
    public void handleSliderReleased(Event event) {
        if (mediaPlayer != null) {
            Slider slider = (Slider)event.getSource();//identifies which slider was moved
            tmp = Double.toString(slider.getValue());
            
            if (slider.equals(firstSlider)) {
                bandList.get(0).setGain(slider.getValue());
                firstLabel.setText(tmp + " dB");
                settings.set(0, slider.getValue());
            }
            
            if (slider.equals(secondSlider)) {
                bandList.get(1).setGain(slider.getValue());
                secondLabel.setText(tmp + " dB");
                settings.set(1, slider.getValue());
            }
            
            if (slider.equals(thirdSlider)) {
                bandList.get(2).setGain(slider.getValue());
                thirdLabel.setText(tmp + " dB");
                settings.set(2, slider.getValue());
            }
            
            if (slider.equals(fourthSlider)) {
                bandList.get(3).setGain(slider.getValue());
                fourthLabel.setText(tmp + " dB");
                settings.set(3, slider.getValue());
            }
            
            if (slider.equals(fifthSlider)) {
                bandList.get(4).setGain(slider.getValue());
                fifthLabel.setText(tmp + " dB");
                settings.set(4, slider.getValue());
            }
            
            if (slider.equals(sixthSlider)) {
                bandList.get(5).setGain(slider.getValue());
                sixthLabel.setText(tmp + " dB");
                settings.set(5, slider.getValue());
            }
            
            if (slider.equals(seventhSlider)) {
                bandList.get(6).setGain(slider.getValue());
                seventhLabel.setText(tmp + " dB");
                settings.set(6, slider.getValue());
            }
            
            if (slider.equals(eighthSlider)) {
                bandList.get(7).setGain(slider.getValue());
                eighthLabel.setText(tmp + " dB");
                settings.set(7, slider.getValue());
            }
            
            if (slider.equals(ninthSlider)) {
                bandList.get(8).setGain(slider.getValue());
                ninthLabel.setText(tmp + " dB");
                settings.set(8, slider.getValue());
            }
            
            if (slider.equals(tenthSlider)) {
                bandList.get(9).setGain(slider.getValue());
                tenthLabel.setText(tmp + " dB");
                settings.set(9, slider.getValue());
            }
        }
    }
}