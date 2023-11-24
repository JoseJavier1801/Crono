module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires java.xml.bind;

    opens org.example to javafx.fxml;
    opens org.example.Model to javafx.base;
    exports org.example;
    exports org.example.Controller;
    opens org.example.Controller to javafx.fxml;
    opens org.example.Conexion to java.xml.bind;

    // Usa esta línea solo si estás utilizando Java 9-11
    // uses java.xml.bind.JAXBContext;
}
