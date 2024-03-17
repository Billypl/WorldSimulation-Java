module com.example.worldsimulationjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.worldsimulationjava to javafx.fxml;
    exports com.example.worldsimulationjava;
}