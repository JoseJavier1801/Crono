package org.example.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.App;
import org.example.Model.Cronometro;
import org.example.Model.CronometroListener;

import java.io.IOException;

public class CronoViewController implements CronometroListener {

    @FXML
    public Label hours;

    @FXML
    public Label minutes;

    @FXML
    public Label seconds;

    @FXML
    public Button start_btn;

    @FXML
    public Button stop_btn;

    @FXML
    public Button restart_btn;

    @FXML
    public Button save_btn;

    @FXML
    public Button change_btn;

    private Cronometro cronometro;

    private Thread cronometroThread;

    public CronoViewController() {
        cronometro = new Cronometro();
        cronometro.setListener(this); // Establecer el controlador como listener
        cronometroThread = new Thread(cronometro);
    }

    @FXML
    private void Start() {
        if (!cronometroThread.isAlive()) {
            cronometro.startCronometro();
            cronometroThread = new Thread(cronometro);
            cronometroThread.start();

            // Actualizar los Label con los valores iniciales del cronómetro
            updateLabels();
        }
    }

    @FXML
    private void Stop() {
        cronometro.stopCronometro();
    }

    @FXML
    private void Restart() {
        cronometro.stopCronometro();
        cronometro.resetAndStartCronometro();
        updateLabels();
    }

    @Override
    public void onTimeChanged(int hours, int minutes, int seconds) {
        Platform.runLater(() -> {
            this.hours.setText(String.format("%02d", hours));
            this.minutes.setText(String.format("%02d", minutes));
            this.seconds.setText(String.format("%02d", seconds));
        });
    }

    private void updateLabels() {
        Platform.runLater(() -> {
            hours.setText(String.format("%02d", cronometro.getMinutes()));
            minutes.setText(String.format("%02d", cronometro.getSeconds()));
            seconds.setText(String.format("%02d", cronometro.getMiliseconds()));
        });
    }

    @FXML
    private void ShowTime() throws IOException {
        App.setRoot("tablecrono");
    }

    @FXML
    private void ChangeView() throws IOException {
        App.setRoot("c");
    }
}
