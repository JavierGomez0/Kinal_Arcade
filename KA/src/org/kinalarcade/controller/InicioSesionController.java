package org.kinalarcade.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.kinalarcade.system.main;


/**
 * FXML Controller class
 *
 * @author danil
 */
public class InicioSesionController implements Initializable {

    private main principal;
    
    @FXML private Button btnMenu, btnRegistro;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void clicManejoEvento(ActionEvent evento){
        //btnMenu
        if (evento.getSource()==btnMenu){
            System.out.println("Nos vamos al Menu Principal");
            principal.menuPrincipal();
        } 
    }
    
}
    

