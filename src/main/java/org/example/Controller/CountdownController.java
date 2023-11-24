package org.example.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.App;
import org.example.Model.Crono;

import java.io.File;
import java.io.IOException;

public class CountdownController {

    @FXML
    private Label milisegundosLabel;

    @FXML
    private Rectangle time;

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

    @FXML
    private Button buttonExit;

    private Crono crono;
    private Timeline timeline;
    private MediaPlayer mediaPlayer;

    public CountdownController() {
        crono = new Crono(0, 0, 0);
        timeline = new Timeline();
        initializeMediaPlayer();
    }

    private void initializeMediaPlayer() {
        String musicFile = "C:\\Users\\FA506\\Music\\miedo.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        // Configurar la reproducción en bucle
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }

    @FXML
    private void startCountdown() {
        if (timeline.getStatus() != Timeline.Status.RUNNING) {
            // Limpiar los KeyFrames existentes antes de agregar uno nuevo
            timeline.getKeyFrames().clear();

            KeyFrame keyFrame = new KeyFrame(Duration.millis(1), event -> {
                updateCountdown(event);
                updateColor();
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            // Reproducir música en bucle
            mediaPlayer.play();

            // Establecer el color a verde
            time.setFill(Color.GREEN);
        }
    }

    @FXML
    private void Exit() throws IOException {
        App.setRoot("CronoView");
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
    private void stopCountdown() {
        timeline.stop();
        // Detener la reproducción de música
        mediaPlayer.stop();

        // Actualizar el color cuando se detiene la cuenta atrás
        updateColor();
    }

    @FXML
    private void restartCountdown() {
        stopCountdown();
        crono = new Crono(0, 0, 0);
        updateLabels();

        // Establecer el color a verde
        time.setFill(Color.GREEN);
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

    private void updateColor() {
        if (timeline.getStatus() == Timeline.Status.STOPPED) {
            // La cuenta atrás ha llegado a cero, establecer el color a rojo
            time.setFill(Color.RED);
        } else {
            // La cuenta atrás está en progreso, establecer el color a verde
            time.setFill(Color.GREEN);
        }
    }
}
