package org.kinalarcade.system;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kinalarcade.controller.BuscaMinasController;
import org.kinalarcade.controller.GameController;
import org.kinalarcade.controller.GameOverController;
import org.kinalarcade.controller.InicioSesionController;
import org.kinalarcade.controller.MenuController;
import org.kinalarcade.controller.MenuPrincipalController;
import org.kinalarcade.controller.PiedraPTController;
import org.kinalarcade.controller.TetrisController;
import org.kinalarcade.controller.WordleController;
import org.kinalarcade.controller.totitoController;

/**
 *
 * @author jgome
 */
public class main extends Application {

    private static String URL_VIEW = "/org/kinalarcade/view/";
    private Stage escenarioPrincipal;

    @Override
    public void start(Stage stage) throws Exception {
        this.escenarioPrincipal = stage;

        inicioSesion();
        stage.setTitle("Kinal Arcade");
        stage.show();
        stage.centerOnScreen();
    }

    public FXMLLoader cambiarEscena(String fxml, double ancho, double alto) {
        FXMLLoader cargadorFXML = null;
        try {
            cargadorFXML = new FXMLLoader(getClass().getResource(URL_VIEW + fxml));
            Parent archivoFXML = cargadorFXML.load();
            Scene escena = new Scene(archivoFXML);
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.sizeToScene();
            escenarioPrincipal.centerOnScreen();
        } catch (Exception ex) {
            System.out.println("Error al cambiar:" + ex.getMessage());
            ex.printStackTrace();
        }
        return cargadorFXML;
    }

    public void inicioSesion() {
        InicioSesionController ins
                = cambiarEscena("InicioSesionView.fxml", 600, 341).getController();
        escenarioPrincipal.setResizable(true);
        ins.setPrincipal(this);

    }

    public void menuPrincipal() {
        MenuPrincipalController mpc
                = cambiarEscena("MenuPrincipalView.fxml", 700, 500).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(700);
        escenarioPrincipal.setHeight(500);
        escenarioPrincipal.centerOnScreen();
        mpc.setPrincipal(this);
    }

    public void juegoWordle() {
        WordleController wdc
                = cambiarEscena("WordleView.fxml", 600, 650).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(600);
        escenarioPrincipal.setHeight(650);
        escenarioPrincipal.centerOnScreen();
        wdc.setPrincipal(this);
    }

    public void juegoTotito() {
        totitoController toc
                = cambiarEscena("totitoView.fxml", 800, 800).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(800);
        escenarioPrincipal.setHeight(800);
        escenarioPrincipal.centerOnScreen();
        toc.setPrincipal(this);
    }

    public void juegoBuscaMinas() {
        BuscaMinasController bmc
                = cambiarEscena("BuscaMinasView.fxml", 750, 800).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(750);
        escenarioPrincipal.setHeight(800);
        escenarioPrincipal.centerOnScreen();
        bmc.setPrincipal(this);
    }

    public void juegoPiedraPT() {
        PiedraPTController ppc
                = cambiarEscena("PiedraPTView.fxml", 700, 400).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(700);
        escenarioPrincipal.setHeight(400);
        escenarioPrincipal.centerOnScreen();
        ppc.setPrincipal(this);
    }

    public void startMenu() {
        MenuController stm
                = cambiarEscena("MenuView.fxml", 900, 600).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(900);
        escenarioPrincipal.setHeight(632);
        escenarioPrincipal.centerOnScreen();
        stm.setPrincipal(this);
    }

    public void startGame() {
        GameController gmc
                = cambiarEscena("GameView.fxml", 900, 600).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(900);
        escenarioPrincipal.setHeight(632);
        escenarioPrincipal.centerOnScreen();
        gmc.setPrincipal(this);
    }

    public void juegoTetris() {
        TetrisController ttc
                = cambiarEscena("tetrisView.fxml", 300, 600).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setHeight(635);
        escenarioPrincipal.setWidth(316);
        escenarioPrincipal.centerOnScreen();
        ttc.setPrincipal(this);
    }

    public void gameOver() {
        GameOverController goc
                = cambiarEscena("gameOver.fxml", 600, 400).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(600);
        escenarioPrincipal.setHeight(400);
        escenarioPrincipal.centerOnScreen();
        goc.setPrincipal(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
