package org.kinalarcade.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.kinalarcade.system.main;

/**
 * FXML Controller class
 *
 * @author danil
 */
public class InicioSesionController implements Initializable {

    @FXML
    private ImageView imageViewFondo;

    @FXML
    private AnchorPane anchorPane;
    private main principal;

    @FXML
    private Button btnMenu, btnRegistro;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageViewFondo.fitWidthProperty().bind(anchorPane.widthProperty());
        imageViewFondo.fitHeightProperty().bind(anchorPane.heightProperty());

    }

    @FXML
    public void clicManejoEvento(ActionEvent evento) {
        //btnMenu
        if (evento.getSource() == btnMenu) {
            System.out.println("Nos vamos al Menu Principal");
            principal.menuPrincipal();
        }
    }

}
