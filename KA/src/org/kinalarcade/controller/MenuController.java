package org.kinalarcade.controller;

import javafx.event.ActionEvent;  // Cambiado para usar JavaFX ActionEvent
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.kinalarcade.system.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Main principal;

    @FXML private Button btnInicio;

    public void setPrincipal(Main principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa si es necesario
    }

    // Método para manejar el evento del botón
    @FXML
    public void manejoEvento(ActionEvent evento) {
        if (evento.getSource() == btnInicio) {
            principal.startGame();  // Llamar al método de la clase Main
        }
    }
}
