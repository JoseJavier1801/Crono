package org.example.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.App;
import org.example.DAO.CronoDAO;
import org.example.Model.Crono;
import org.example.Model.Cronometro;
import org.example.Model.CronometroListener;

import java.io.IOException;

/**
 * Controlador que gestiona  la interfaz gráfica del cronómetro.
 */
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
    public Button table_btn;

    @FXML
    public Button change_btn;

    private Cronometro cronometro;

    private Thread cronometroThread;

    CronoDAO CDAO=new CronoDAO();


    /**
     * Constructor del controlador. Inicializa el cronómetro y el hilo asociado.
     */
    public CronoViewController() {
        cronometro = new Cronometro();
        cronometro.setListener(this); // Establecer el controlador como listener
        cronometroThread = new Thread(cronometro);
    }

    /**
     * Inicia el cronómetro y actualiza los Labels con los valores iniciales.
     */
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

    /**
     * Detiene el cronómetro.
     */
    @FXML
    private void Stop() {
        cronometro.stopCronometro();
    }

    /**
     * Reinicia el cronómetro y actualiza los Labels.
     */
    @FXML
    private void Restart() {
        cronometro.stopCronometro();
        cronometro.resetAndStartCronometro();
        updateLabels();
    }

    /**
     * Método llamado cuando cambia el tiempo en el cronómetro. Actualiza los Labels en la interfaz gráfica.
     *
     * @param hours   Horas
     * @param minutes Minutos
     * @param seconds Segundos
     */
    @Override
    public void onTimeChanged(int hours, int minutes, int seconds) {
        Platform.runLater(() -> {
            this.hours.setText(String.format("%02d", hours));
            this.minutes.setText(String.format("%02d", minutes));
            this.seconds.setText(String.format("%02d", seconds));
        });
    }

    /**
     * Actualiza los Labels en la interfaz gráfica con los valores actuales del cronómetro.
     */
    private void updateLabels() {
        Platform.runLater(() -> {
            hours.setText(String.format("%02d", cronometro.getMinutes()));
            minutes.setText(String.format("%02d", cronometro.getSeconds()));
            seconds.setText(String.format("%02d", cronometro.getMiliseconds()));
        });
    }

    /**
     * Cambia a la vista de la tabla de tiempos.
     *
     * @throws IOException Si hay un error al cargar la vista.
     */
    @FXML
    private void ShowTime() throws IOException {
        App.setRoot("tablecrono");
    }

    /**
     * Cambia a otra vista.
     *
     * @throws IOException Si hay un error al cargar la vista.
     */
    @FXML
    private void ChangeView() throws IOException {
        App.setRoot("c");
    }
    @FXML
    private void SaveTime() throws IOException{
        int hours = Integer.parseInt(this.hours.getText());
        int minutes = Integer.parseInt(this.minutes.getText());
        int seconds = Integer.parseInt(this.seconds.getText());

        Crono C = new Crono(hours, minutes, seconds);
        if(CDAO!=null){
            CDAO.add(C);
            showAlert("Tiempo guardado en la base de datos.");
        }else {
            showAlert("No se ha podido añadir el tiempo");
        }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
