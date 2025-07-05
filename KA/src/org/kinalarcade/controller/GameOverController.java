/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.kinalarcade.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.kinalarcade.system.main;

/**
 * FXML Controller class
 *
 * @author Zacarias
 */
public class GameOverController implements Initializable {
    
    private main principal;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }
    
    @FXML
    private Button btnMenu, btnReiniciar;
    @FXML
    private TextField txtPuntos;
    private TetrisController tc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void regresarMenu(ActionEvent evento){
        if (evento.getSource()== btnMenu){
            System.out.println("Nos vamos a Menu Principal");
            principal.menuPrincipal();
        } else if (evento.getSource() == btnReiniciar) {
            System.out.println("Volviendo a tetris");
            principal.juegoTetris();
        }
    }
    
    public void setPuntos(int puntos){
        txtPuntos.setText(String.valueOf(puntos));
    }
    
}
