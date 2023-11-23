package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.DAO.CronoDAO;
import org.example.Model.Crono;

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

    private CronoDAO cronoDAO;

    @FXML
    public void initialize() {
        CronoDAO cronoDAO = new CronoDAO();

        // Configura las celdas de la tabla para que obtengan datos de las propiedades de Crono.
        milisegundosColumn.setCellValueFactory(new PropertyValueFactory<>("milisegundos"));
        minutesColumn.setCellValueFactory(new PropertyValueFactory<>("minutes"));
        secondsColumn.setCellValueFactory(new PropertyValueFactory<>("seconds"));

        // Limpia la tabla antes de agregar nuevos elementos
        cronoTable.getItems().clear();

        // Llena la tabla con datos desde la base de datos.
        showCronoData();
    }

    private void showCronoData() {
        List<Crono> cronos = cronoDAO.showAll(); // Cambiado de getAllCronos() a showAll()
        cronoTable.getItems().addAll(cronos);
    }
}
