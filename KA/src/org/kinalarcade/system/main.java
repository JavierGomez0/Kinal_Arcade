/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.kinalarcade.system;

/**
 *
 * @author jgome
 */

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kinalarcade.controller.Controlador;

/**
 *
 * @author informatica
 */
public class Main extends Application {

    private Stage escenarioPrincipal;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenario) throws Exception {
        this.escenarioPrincipal = escenario;
        unoVs1();
        escenario.show();
        escenario.getIcons().add(new Image("/org/migueltamat/image/logo_piedra_papel_y_tijera-removebg-preview.png"));

    }

    public FXMLLoader cambiarEscena(String fxml, double Ancho, double Alto) {
        FXMLLoader cargadorFXML = null;
        try {
            cargadorFXML = new FXMLLoader(getClass().getResource("/org/kinalarcade/view/" + fxml));
            Parent ArchivoFXML = cargadorFXML.load();
            Scene escena = new Scene(ArchivoFXML, Ancho, Alto);
            escenarioPrincipal.setScene(escena);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return cargadorFXML;
    }

    public void unoVs1() {
        Controlador un = cambiarEscena("PiedraPapelYTijera.view.fxml", 700, 400).getController();
        un.setPrincipal(this);
    }

   
    
}

