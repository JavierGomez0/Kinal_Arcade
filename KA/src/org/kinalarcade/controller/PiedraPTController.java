/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kinalarcade.controller;

/**
 *
 * @author MIGUEL
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kinalarcade.system.main;

/**
 *
 * @author Miguel Tamat
 */
public class PiedraPTController implements Initializable {
    
    private main principal;

    @FXML
    private Label resultado;
    private PauseTransition pausa;
    @FXML
    private Button btnMenu;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    public static String jugar(String jugador) {
        String[] opciones = {"Piedra", "Papel", "Tijera"};
        Random random = new Random();

        String maquina = opciones[random.nextInt(3)];

        if (jugador.equals(maquina)) {
            return "Empate ambos elijieron: " + jugador;
        } else if ((jugador.equals("Piedra") && maquina.equals("Tijera"))
                || (jugador.equals("Papel") && maquina.equals("Piedra"))
                || (jugador.equals("Tijera") && maquina.equals("Papel"))) {
            return "Ganaste la maquina eligio " + maquina;
        } else {
            return "Perdiste maquina eligio " + maquina;
        }
    }

    @FXML
    private void jugarPiedra() {
        manejarClic();
        resultado.setText(jugar("Piedra"));
    }

    @FXML
    private void jugarPapel() {
        manejarClic();
        resultado.setText(jugar("Papel"));
    }

    @FXML
    private void jugarTijera() {
        manejarClic();
        resultado.setText(jugar("Piedra"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pausa = new PauseTransition(Duration.seconds(2));
        pausa.setOnFinished(event -> resultado.setText("Elija su jugada..."));

    }

    @FXML
    private void manejarClic() {
        pausa.stop();

        pausa.play();
    }
    
    @FXML
    public void clicRegresarP(ActionEvent evento){
        if (evento.getSource()==btnMenu){
            System.out.println("Nos vamos al Menu Principal");
            principal.menuPrincipal();
        }
    }   
}