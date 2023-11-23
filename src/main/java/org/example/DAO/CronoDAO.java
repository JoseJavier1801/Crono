package org.example.DAO;

import org.example.Conexion.Connect;
import org.example.Model.Crono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CronoDAO implements IDAO<Crono> {
    private final static String FIND_ALL = "SELECT * FROM time";
    private final static String add = "INSERT INTO `time` (`milisegundos`, `minutos`, `segundos`) VALUES (?, ?, ?)";

    private Connection conn;
    public CronoDAO(Connection conn) {
        this.conn = conn;
    }
    public CronoDAO() {
        this.conn= Connect.getConnect();
    }




    @Override
    public List<Crono> showAll() {
        List<Crono> cronos = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int milisegundos = resultSet.getInt("milisegundos");
                int minutes = resultSet.getInt("minutes");
                int seconds = resultSet.getInt("seconds");

                Crono crono = new Crono(milisegundos, minutes, seconds);
                cronos.add(crono);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cronos;
    }

    @Override
    public void add(Crono entity) {
        try (PreparedStatement statement = conn.prepareStatement(add)) {
            statement.setInt(1, entity.getMilisegundos());
            statement.setInt(2, entity.getMinutes());
            statement.setInt(3, entity.getSeconds());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Crono entity) {
        // Implementa la lógica para eliminar un Crono de la base de datos
    }
}
