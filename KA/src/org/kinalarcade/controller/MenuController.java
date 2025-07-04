
package org.kinalarcade.controller;

import javafx.event.ActionEvent;  
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.kinalarcade.system.main;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private main principal;

    @FXML private Button btnInicio, btnMenu;

    public void setPrincipal(main principal) {
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
