package org.kinalarcade.system;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kinalarcade.controller.BuscaMinasController;
import org.kinalarcade.controller.GameController;
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
public class main extends Application{
    
    private static String URL_VIEW = "/org/kinalarcade/view/";
    private Stage escenarioPrincipal;

    @Override
    public void start(Stage stage) throws Exception {
        this.escenarioPrincipal = stage;
      
        inicioSesion();
        stage.setTitle("Kinal Arcade");
        stage.show();
    }
    
     public FXMLLoader cambiarEscena(String fxml, double ancho, double alto){
            FXMLLoader cargadorFXML = null;
        try {
            cargadorFXML = new FXMLLoader(getClass().getResource(URL_VIEW+fxml));
            Parent archivoFXML = cargadorFXML.load();
            Scene escena = new Scene(archivoFXML,ancho,alto);
            escenarioPrincipal.setScene(escena);
        } catch (Exception ex) {
            System.out.println("Error al cambiar:" + ex.getMessage());
            ex.printStackTrace();
        }
        return cargadorFXML;
    }
    
    public void inicioSesion(){
        InicioSesionController ins = 
                    cambiarEscena("InicioSesionView.fxml", 600, 341).getController();
        ins.setPrincipal(this);
        
    }
    
    public void menuPrincipal(){
        MenuPrincipalController mpc = 
                    cambiarEscena("MenuPrincipalView.fxml", 786, 532 ).getController();
        mpc.setPrincipal(this);
    }
    
    public void juegoWordle(){
        WordleController wdc =
                    cambiarEscena("WordleView.fxml",600 ,650).getController();
        wdc.setPrincipal(this);
    }
    
    public void juegoTotito(){
        totitoController toc = 
                    cambiarEscena("totitoView.fxml",800,800).getController();
        toc.setPrincipal(this);
    }
    
    public void juegoBuscaMinas(){
        BuscaMinasController bmc =
                    cambiarEscena("BuscaMinasView.fxml",750,800).getController();
        bmc.setPrincipal(this);
    }
    
    public void juegoPiedraPT(){
        PiedraPTController ppc =
                    cambiarEscena("PiedraPTView.fxml",700,400).getController();
        ppc.setPrincipal(this);
    }
    
    public void startMenu(){
        MenuController stm =
                    cambiarEscena("MenuView.fxml",406,300).getController();
        stm.setPrincipal(this);
    }
    
    public void startGame(){
        GameController gmc =
                    cambiarEscena("GameView.fxml",370,370).getController();
        gmc.setPrincipal(this);
    }
    
    public void juegoTetris(){
        TetrisController ttc =
                    cambiarEscena("tetrisView.fxml",300,600).getController();
        ttc.setPrincipal(this);
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}
