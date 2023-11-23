package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.DAO.CronoDAO;
import org.example.Model.Crono;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ControllerShowCrono {

    @FXML
    private TableView<Crono> cronoTable;

    @FXML
    private TableColumn<Crono, Integer> milisegundosColumn;

    @FXML
    private TableColumn<Crono, Integer> minutesColumn;

    @FXML
    private TableColumn<Crono, Integer> secondsColumn;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonDelete;

    // Corrige la declaración de la variable cronoDAO
    private CronoDAO cronoDAO = new CronoDAO();

    // Variable para almacenar la ID seleccionada
    private int selectedCronoId = -1;

    @FXML
    private void Exit() throws IOException {
        App.setRoot("CronoView");
    }

    @FXML
    private void handleCronoClick() {
        Crono selectedCrono = cronoTable.getSelectionModel().getSelectedItem();
        if (selectedCrono != null) {
            // Almacena la ID del Crono seleccionado
            selectedCronoId = selectedCrono.getId(); // Asegúrate de tener un método getId() en tu clase Crono
        }
    }

    @FXML
    private void handleDeleteButtonClick() {
        if (selectedCronoId != -1) {
            // Elimina el Crono de la base de datos usando la ID almacenada
            Crono cronToDelete = new Crono();
            cronToDelete.setId(selectedCronoId);
            cronoDAO.delete(cronToDelete);

            // Limpia la selección y actualiza la tabla
            selectedCronoId = -1;
            cronoTable.getItems().clear();
            showCronoData();
        }
    }


    @FXML
    public void initialize() {
        // Configura las celdas de la tabla para que obtengan datos de las propiedades de Crono.
        milisegundosColumn.setCellValueFactory(new PropertyValueFactory<>("milisegundos"));
        minutesColumn.setCellValueFactory(new PropertyValueFactory<>("minutes"));
        secondsColumn.setCellValueFactory(new PropertyValueFactory<>("seconds"));

        // Limpia la tabla antes de agregar nuevos elementos
        cronoTable.getItems().clear();

        // Llena la tabla con datos desde la base de datos.
        showCronoData();

        // Configura el manejador de eventos para clics en la tabla
        cronoTable.setOnMouseClicked(event -> handleCronoClick());
    }

    private void showCronoData() {
        List<Crono> cronos = cronoDAO.showAll();
        cronoTable.getItems().addAll(cronos);
    }
}
