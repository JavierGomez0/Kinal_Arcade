package org.kinalarcade.system;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kinalarcade.controller.MenuController;
import org.kinalarcade.controller.GameController;

public class Main extends Application {

    private static final String URL_VIEW = "/org/kinalarcade/View/";
    private Stage escenarioPrincipal;

    public static void main(String[] args) {
        launch(args); // <- Estes método lanza la aplicación JavaFX
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.escenarioPrincipal = stage;
        Menu(); // Cambia a la vista GameView.fxml
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public FXMLLoader cambiarEscena(String fxml, double ancho, double alto) {
        FXMLLoader cargadorFXML = null;
        try {
            cargadorFXML = new FXMLLoader(getClass().getResource(URL_VIEW + fxml));
            Parent archivoFXML = cargadorFXML.load();
            Scene escena = new Scene(archivoFXML, ancho, alto);
            escenarioPrincipal.setScene(escena);
        } catch (IOException ex) {
            System.out.println("Error al cambiar: " + ex.getMessage());
            ex.printStackTrace();
        }
        return cargadorFXML;
    }
    
    
    public void Menu() {
        FXMLLoader loader = cambiarEscena("MenuView.fxml", 400, 300);
        if (loader != null) {
            MenuController mpc = loader.getController();
            mpc.setPrincipal(this);
        }
    }
    public void Game() {
        FXMLLoader loader = cambiarEscena("GameView.fxml", 369, 370);
        if (loader != null) {
            GameController mpc = loader.getController();
            mpc.setPrincipal(this);
        }
    }

    public void startGame() {
       Game();
    }
}
