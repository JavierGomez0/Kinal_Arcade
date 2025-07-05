package org.kinalarcade.system;

import java.io.InputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kinalarcade.controller.BuscaMinasController;
import org.kinalarcade.controller.GameController;
import org.kinalarcade.controller.GameOverController;
import org.kinalarcade.controller.InicioSesionController;
import org.kinalarcade.controller.MenuController;
import org.kinalarcade.controller.MenuPrincipalController;
import org.kinalarcade.controller.PiedraPTController;
import org.kinalarcade.controller.ReproductorMusica;
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
    private ReproductorMusica musica;

    @Override
    public void start(Stage stage) throws Exception {
        this.escenarioPrincipal = stage;
        this.musica = new ReproductorMusica();

        InputStream iconStream = getClass().getResourceAsStream("/org/kinalarcade/image/logoKinalArcade.png");
        Image icon = new Image(iconStream);
        inicioSesion();
        stage.setTitle("Kinal Arcade");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.centerOnScreen();
        stage.getIcons().add(icon);
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
        musica.detenerMusica();
        MenuPrincipalController mpc
                = cambiarEscena("MenuPrincipalView.fxml", 700, 500).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(700);
        escenarioPrincipal.setHeight(500);
        escenarioPrincipal.centerOnScreen();
        mpc.setPrincipal(this);
        musica.reproducirMusica("music_menu.mp3");
    }

    public void juegoWordle() {
        musica.detenerMusica();
        WordleController wdc
                = cambiarEscena("WordleView.fxml", 600, 650).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(600);
        escenarioPrincipal.setHeight(650);
        escenarioPrincipal.centerOnScreen();
        wdc.setPrincipal(this);
        musica.reproducirMusica("music_wordle.mp3");

    }

    public void juegoTotito() {
        musica.detenerMusica();
        totitoController toc
                = cambiarEscena("totitoView.fxml", 800, 800).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(800);
        escenarioPrincipal.setHeight(800);
        escenarioPrincipal.centerOnScreen();
        toc.setPrincipal(this);
        musica.reproducirMusica("music_totito.mp3");

    }

    public void juegoBuscaMinas() {
        musica.detenerMusica();
        BuscaMinasController bmc
                = cambiarEscena("BuscaMinasView.fxml", 750, 800).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(750);
        escenarioPrincipal.setHeight(800);
        escenarioPrincipal.centerOnScreen();
        bmc.setPrincipal(this);
        musica.reproducirMusica("music_buscaminas.mp3");
    }

    public void juegoPiedraPT() {
        musica.detenerMusica();
        PiedraPTController ppc
                = cambiarEscena("PiedraPTView.fxml", 700, 400).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(700);
        escenarioPrincipal.setHeight(400);
        escenarioPrincipal.centerOnScreen();
        ppc.setPrincipal(this);
        musica.reproducirMusica("music_ppt.mp3");

    }

    public void startMenu() {
        musica.detenerMusica();
        MenuController stm
                = cambiarEscena("MenuView.fxml", 900, 600).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(900);
        escenarioPrincipal.setHeight(632);
        escenarioPrincipal.centerOnScreen();
        stm.setPrincipal(this);
        musica.reproducirMusica("music_preguntas.mp3");

    }

    public void startGame() {
        musica.detenerMusica();
        GameController gmc
                = cambiarEscena("GameView.fxml", 900, 600).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(900);
        escenarioPrincipal.setHeight(632);
        escenarioPrincipal.centerOnScreen();
        gmc.setPrincipal(this);
        musica.reproducirMusica("music_preguntas (2).mp3");
        

    }

    public void juegoTetris() {
        musica.detenerMusica();
        TetrisController ttc
                = cambiarEscena("tetrisView.fxml", 660, 630).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setHeight(660);
        escenarioPrincipal.setWidth(630);
        escenarioPrincipal.centerOnScreen();
        ttc.setPrincipal(this);
        musica.reproducirMusica("music_Tetris.mp3");
    }

    public void gameOver(int puntos) {
        musica.detenerMusica();
        GameOverController goc
                = cambiarEscena("gameOver.fxml", 600, 400).getController();
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.setWidth(600);
        escenarioPrincipal.setHeight(400);
        escenarioPrincipal.centerOnScreen();
        goc.setPrincipal(this);
        goc.setPuntos(puntos);
        musica.musicaRepetir("music_gameover.mp3",1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
