package org.example.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.example.Model.Crono;

public class CountdownController {

    @FXML
    private Label milisegundosLabel;

    @FXML
    private Label minutesLabel;

    @FXML
    private Label secondsLabel;

    @FXML
    private Button stopp_btn;

    @FXML
    private Button startbtn;

    @FXML
    private Button Restartbutton;

    @FXML
    private Button sumbtnmin;

    @FXML
    private Button resbtnmin;

    @FXML
    private Button sumbtnsecon;

    @FXML
    private Button resbtnsecon;

    @FXML
    private Button sumbtseconmin;

    @FXML
    private Button resbtnminseconmin;

    private Crono crono;
    private Timeline timeline;

    public CountdownController() {
        crono = new Crono(0, 0, 0);
        timeline = new Timeline();
    }

    @FXML
    private void incrementMinutes() {
        crono.setMinutes(crono.getMinutes() + 1);
        updateLabels();
    }

    @FXML
    private void decrementMinutes() {
        if (crono.getMinutes() > 0) {
            crono.setMinutes(crono.getMinutes() - 1);
            updateLabels();
        }
    }

    @FXML
    private void incrementSeconds() {
        crono.setSeconds(crono.getSeconds() + 1);
        updateLabels();
    }

    @FXML
    private void decrementSeconds() {
        if (crono.getSeconds() > 0) {
            crono.setSeconds(crono.getSeconds() - 1);
            updateLabels();
        }
    }

    @FXML
    private void incrementMilisegundos() {
        crono.setMilisegundos(crono.getMilisegundos() + 1);
        updateLabels();
    }

    @FXML
    private void decrementMilisegundos() {
        if (crono.getMilisegundos() > 0) {
            crono.setMilisegundos(crono.getMilisegundos() - 1);
            updateLabels();
        }
    }

    @FXML
    private void startCountdown() {
        if (timeline.getStatus() != Timeline.Status.RUNNING) {
            // Limpiar los KeyFrames existentes antes de agregar uno nuevo
            timeline.getKeyFrames().clear();

            KeyFrame keyFrame = new KeyFrame(Duration.millis(1), this::updateCountdown);
            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }


    @FXML
    private void stopCountdown() {
        timeline.stop();
    }

    @FXML
    private void restartCountdown() {
        stopCountdown();
        crono = new Crono(0, 0, 0);
        updateLabels();
    }


    private void updateCountdown(ActionEvent event) {
        if (crono.getMilisegundos() > 0) {
            decrementMilisegundos();
        } else if (crono.getSeconds() > 0) {
            decrementSeconds();
            crono.setMilisegundos(999);
        } else if (crono.getMinutes() > 0) {
            decrementMinutes();
            crono.setSeconds(59);
            crono.setMilisegundos(999);
        } else {
            stopCountdown();
        }
    }

    private void updateLabels() {
        minutesLabel.setText(String.format("%02d", crono.getMinutes()));
        secondsLabel.setText(String.format("%02d", crono.getSeconds()));
        milisegundosLabel.setText(String.format("%03d", crono.getMilisegundos()));
    }
}
