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
import org.kinalarcade.model.FabricaTetrominos;
import org.kinalarcade.model.Tetromino;
import org.kinalarcade.system.main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class TetrisController implements Initializable {

    // Crear las variables necesarias para el juego.
    private int baseX = 3;
    private int baseY = 0;
    private main principal;
    private String teclaPresionada;
    private int posicionX = 5;
    private int posicionY = 0;
    private int[][] tablero = new int[20][10];
    private Rectangle[][] bloquesVisibles = new Rectangle[20][10];
    private Rectangle[] bloquesTetromino = new Rectangle[4];
    private Tetromino actual;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    @FXML
    private Pane gamePane;
    private Timeline timeline;

    // Metodo para que se pueda mover automaticamente el tetromino mediante Timeline.
    private void movimientoAutomatico() {
        crearTetromino();
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            puedeBajar();
        }));
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();
    }

    // Metodo para comprobar si puede o no bajar el tetromino
    private void puedeBajar() {
        if (validarColisiones(actual.getForma(), baseX, baseY + 1)) {
            baseY++;
            actualizarVista(actual.getForma());
        } else {
            fijarTetromino();
            comprobarFilaCompleta();
            crearTetromino();
        }
    }

    // Metodo para fijar el tretromino en una posicion
    private void fijarTetromino() {
        for (Rectangle r : bloquesTetromino) {
            int fila = (int) (r.getY() / 30);
            int columna = (int) (r.getX() / 30);
            tablero[fila][columna] = 1;
        }
    }

    // Metodo para asignar color al Tetromino
    private Color colorPorCodigo(int code) {
        switch (code) {
            case 1:
                return Color.AQUA;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.PURPLE;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.RED;
            case 6:
                return Color.BLUE;
            case 7:
                return Color.ORANGE;
            default:
                return Color.GRAY;
        }
    }

    // Metodo para poder mover el tetromino mediante la deteccion de eventos de teclado.
    private void moverRectangulo() {
        gamePane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> {
                    if (validarColisiones(actual.getForma(), baseX - 1, baseY)) {
                        baseX--;
                        actualizarVista(actual.getForma());
                    }
                    break;
                }
                case RIGHT -> {
                    if (validarColisiones(actual.getForma(), baseX + 1, baseY)) {
                        baseX++;
                        actualizarVista(actual.getForma());
                    }
                    break;
                }
                case DOWN -> {
                    if (validarColisiones(actual.getForma(), baseX, baseY + 1)) {
                        baseY++;
                        actualizarVista(actual.getForma());
                    }
                    break;
                }

                case R -> {
                    int[][] formaRotada = actual.rotarTetromino(actual.getForma());
                    if (validarColisiones(formaRotada, baseX, baseY)) {
                        actual.setForma(formaRotada);
                        actualizarVista(formaRotada);
                    }
                    break;
                }
            }
        });

        gamePane.setFocusTraversable(true);
    }

    private boolean validarColisiones(int[][] nuevaForma, int nuevaBaseX, int nuevaBaseY) {
        for (int i = 0; i < nuevaForma.length; i++) {
            for (int j = 0; j < nuevaForma[0].length; j++) {
                if (nuevaForma[i][j] != 0) {
                    int x = nuevaBaseX + j;
                    int y = nuevaBaseY + i;

                    if (x < 0 || x >= 10 || y < 0 || y >= 20) {
                        return false;
                    }
                    if (tablero[y][x] != 0) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    private void actualizarVista(int[][] forma) {
        int index = 0;
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[0].length; j++) {
                if (forma[i][j] != 0) {
                    Rectangle r = bloquesTetromino[index++];
                    r.setX((baseX + j) * 30);
                    r.setY((baseY + i) * 30);
                }
            }
        }
    }
    
    private void comprobarFilaCompleta(){
        for (int fila = 19; fila >= 0; fila--) {
            boolean completa = true;
            for (int col = 0; col < 10; col++) {
                if (tablero[fila][col] == 0){
                    completa = false;
                    break;
                }
            }
            
            if (completa){
                System.out.println("Fila Completa");
                fila++;
            }
        }
    }

    private void crearTetromino() {
        actual = FabricaTetrominos.formaAleatoria();
        int[][] forma = actual.getForma();
        int index = 0;
        baseX = 3;
        baseY = 0;
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[0].length; j++) {
                if (forma[i][j] != 0) {
                    Rectangle bloque = new Rectangle(30, 30);
                    bloque.setX((baseX + j) * 30);
                    bloque.setY((baseY + i) * 30);
                    bloque.setFill(colorPorCodigo(forma[i][j]));
                    bloque.setStroke(Color.BLACK);
                    bloquesTetromino[index++] = bloque;
                    gamePane.getChildren().add(bloque);
                }
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    // Inicializar los metodos creados previamente.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        movimientoAutomatico();
        moverRectangulo();
    }
}
