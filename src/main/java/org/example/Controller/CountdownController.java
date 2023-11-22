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
    private Button stop_btn;

    @FXML
    private Button restart_btn;

    @FXML
    private Button sumbtn;

    @FXML
    private Button resbtn;

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

    @FXML
    private void updateCountdown(ActionEvent event) {
        if (crono.getMilisegundos() > 0 || crono.getMinutes() > 0 || crono.getSeconds() > 0) {
            decrementMilisegundos();
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
