package org.kinalarcade.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kinalarcade.system.main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class TetrisController implements Initializable {

    // Crear las variables necesarias para el juego.
    private main principal;
    private String teclaPresionada;
    private int posicionX = 5;
    private int posicionY = 0;
    int[][] tablero = new int[20][10];
    Rectangle[][] bloquesVisibles = new Rectangle[20][10];

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    @FXML
    private Pane gamePane;
    private Timeline timeline;

    // Metodo para que se pueda mover automaticamente el tetromino mediante Timeline.
    private void configurarMovimiento() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle bloque = new Rectangle(30, 30);
                bloque.setX(j * 30);
                bloque.setY(i * 30);
                bloque.setFill(Color.TRANSPARENT);
                bloquesVisibles[i][j] = bloque;
                gamePane.getChildren().add(bloque);
            }
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            if (posicionY > 0) {
                bloquesVisibles[posicionY - 1][posicionX].setFill(Color.TRANSPARENT);
            }

            if (posicionY<18) {
                bloquesVisibles[posicionY][posicionX].setFill(Color.GREEN);
                posicionY++;
            } else {
                bloquesVisibles[19][posicionX].setFill(Color.GREEN);
                tablero[19][posicionX] = 1;
                timeline.stop();
                crearTetromino();
            }
        }));
        timeline.setCycleCount(19);
        timeline.play();

    }

    // Metodo para poder mover el tetromino mediante la deteccion de eventos de teclado.
    private void moverRectangulo() {
        gamePane.setOnKeyPressed(event -> {
            if (posicionY < 19) {
                switch (event.getCode()) {
                    case LEFT:
                        if (posicionX > 0 && posicionY > 0) {
                            bloquesVisibles[posicionY - 1][posicionX].setFill(Color.TRANSPARENT);
                            posicionX--;
                            bloquesVisibles[posicionY - 1][posicionX].setFill(Color.GREEN);
                        }
                        break;
                    case RIGHT:
                        if (posicionX < 9 && posicionY > 0) {
                            bloquesVisibles[posicionY - 1][posicionX].setFill(Color.TRANSPARENT);
                            posicionX++;
                            bloquesVisibles[posicionY - 1][posicionX].setFill(Color.GREEN);
                        }
                        break;
                    case DOWN:
                        if (posicionY < 19 && posicionY > 0) {
                            bloquesVisibles[posicionY - 1][posicionX].setFill(Color.TRANSPARENT);
                            posicionY++;
                            bloquesVisibles[posicionY - 1][posicionX].setFill(Color.GREEN);
                            if (posicionY == 19){
                                System.out.println("Posicion marcada en: "+tablero[posicionY][posicionX]);
                            }
                        }
                        break;
                }
            }
        });

        gamePane.setFocusTraversable(true);
    }

    private void crearTetromino() {
        posicionX = 5;
        posicionY = 0;
        timeline.playFromStart();
    }

    /**
     * Initializes the controller class.
     */
    // Inicializar los metodos creados previamente.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarMovimiento();
        moverRectangulo();
    }
}