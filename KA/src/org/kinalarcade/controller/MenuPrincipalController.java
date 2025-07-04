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
public class MenuPrincipalController implements Initializable {

    private main principal;
    
    @FXML private Button btnWordle, btnTotito, btnBuscaM, btnPiedraPT, btnPreguntas, btnTetris;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void clicWordle(ActionEvent evento){
        if (evento.getSource()==btnWordle){
            System.out.println("Nos vamos al Juego Wordle");
            principal.juegoWordle();
        
        }else if (evento.getSource()== btnTotito){
            System.out.println("Nos vamos al Juego Totito");
            principal.juegoTotito();
            
        }else if (evento.getSource()== btnBuscaM){
            System.out.println("Nos vamos al Juego Busca Minas");
            principal.juegoBuscaMinas();
        }else if (evento.getSource()== btnPiedraPT){
            System.out.println("Nos vamos al Juegro Piedra Papel o Tijera");
            principal.juegoPiedraPT();
        }else if (evento.getSource()== btnPreguntas){
            System.out.println("Nos vamos a Preguntas");
            principal.startMenu();
        }else if (evento.getSource()== btnTetris){
            System.out.println("Nos vamos al Juego Tetris");
            principal.juegoTetris();
        }
    }
    
}
