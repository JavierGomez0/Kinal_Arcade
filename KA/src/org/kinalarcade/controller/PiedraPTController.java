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
    private Label resultado, maquinas, jugadors;
    private PauseTransition pausa;
    @FXML
    private Button btnMenu;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    public static String jugar(String jugador, String maquina) {
        if (jugador.equals(maquina)) {
            return "Empate, ambos eligieron: " + jugador;
        } else if ((jugador.equals("Piedra") && maquina.equals("Tijera"))
                || (jugador.equals("Papel") && maquina.equals("Piedra"))
                || (jugador.equals("Tijera") && maquina.equals("Papel"))) {
            return "Ganaste, la máquina eligió " + maquina;
        } else {
            return "Perdiste, la máquina eligió " + maquina;
        }
    }

    @FXML
    private void jugarPiedra() {
        manejarClic("Piedra");
    }

    @FXML
    private void jugarPapel() {
        manejarClic("Papel");
    }

    @FXML
    private void jugarTijera() {
        manejarClic("Tijera");
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
    public void clicRegresarP(ActionEvent evento) {
        if (evento.getSource() == btnMenu) {
            System.out.println("Nos vamos al Menu Principal");
            principal.menuPrincipal();
        }
    }

    @FXML
    private Button btnPiedra, btnPapel, btnTijera;
    @FXML
    private javafx.scene.image.ImageView imgMaquina;
    @FXML
    private javafx.scene.image.ImageView imgJugador;

    @FXML
    private void manejarClic(String jugador) {
        // Ocultar los otros botones
        btnPiedra.setVisible(false);
        btnPapel.setVisible(false);
        btnTijera.setVisible(false);

        // Mostrar imagen de la máquina
        String[] opciones = {"Piedra", "Papel", "Tijera"};
        Random random = new Random();
        String maquina = opciones[random.nextInt(3)];

        String pathJugador = "/org/kinalarcade/image/" + jugador.toLowerCase() + ".png";
        String pathMaquina = "/org/kinalarcade/image/" + maquina.toLowerCase() + ".png";

        // Mostrar las jugadas
        jugadors.setText("Jugador");
        maquinas.setText("Maquina");

        try {
            imgJugador.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream(pathJugador)));
            imgJugador.setVisible(true);

            imgMaquina.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream(pathMaquina)));
            imgMaquina.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error cargando imágenes:");
            e.printStackTrace();
        }
        // Mostrar resultado
        resultado.setText(jugar(jugador, maquina));

        // Después de 5 segundos, volver a mostrar botones y ocultar imagen
        PauseTransition pausaReset = new PauseTransition(Duration.seconds(3));
        pausaReset.setOnFinished(event -> {
            btnPiedra.setVisible(true);
            btnPapel.setVisible(true);
            btnTijera.setVisible(true);
            imgMaquina.setVisible(false);
            imgJugador.setVisible(false);
            resultado.setText("Elija su jugada...");
            jugadors.setText("");
            maquinas.setText("");

        });
        pausaReset.play();
    }

}
