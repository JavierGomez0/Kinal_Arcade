package org.kinalarcade.system;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kinalarcade.controller.TetrisController;

/**
 *
 * @author Zacarias
 */
public class main extends Application {

    /**
     * @param args the command line arguments
     */
            
    private final String URL = "/org/alessandrozac/view/";
    private Stage escenarioPrincipal;
    private Scene escena;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.escenarioPrincipal = stage;
        tetris();
        escenarioPrincipal.setTitle("Tetris");
        escenarioPrincipal.show();
    }

    public Initializable cambiarEscena(String fxml, double ancho, double alto) throws IOException {
        Initializable interfazCargada = null;
        FXMLLoader cargadorFXML = new FXMLLoader();

        InputStream archivoFXML = main.class.getResourceAsStream(URL + fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(main.class.getResource(URL + fxml));

        escena = new Scene(cargadorFXML.load(archivoFXML), ancho, alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();//Usar el tamaño de la escena en el Stage

        interfazCargada = cargadorFXML.getController();

        return interfazCargada;
    }
    
    public void tetris(){
        try {
            TetrisController tc = (TetrisController) cambiarEscena("tetrisView.fxml", 300, 600);
            tc.setPrincipal(this);
        } catch (Exception e) {
            System.out.println("Error al cambiar escena");
            e.printStackTrace();
        }
    }
}
