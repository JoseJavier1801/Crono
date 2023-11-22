package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.App;

import java.io.IOException;

public class CronoViewController {

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
    private void Start() throws IOException {

    }

    @FXML
    private void Stop() throws IOException {

    }

    @FXML
    private void Restart() throws IOException {

    }

    @FXML
    private void ShowTime() throws IOException {
        App.setRoot("TableTimeView");
    }

}
