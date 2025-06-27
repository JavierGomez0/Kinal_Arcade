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
import org.alessandrozac.model.FabricaTetrominos;
import org.alessandrozac.model.Tetromino;
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
        timeline.setCycleCount(19);
        timeline.play();
    }
    
    private void puedeBajar(){
        boolean colision = false;
        for (Rectangle r : bloquesTetromino){
            double nuevaY = r.getY() + 30;
            
            int fila = (int) (nuevaY / 30);
            int columna = (int) (r.getX() / 30);
            
            if (fila >= 20 || tablero[fila][columna] == 1){
                colision = true;
                break;
            }
        }
        
        if (colision){
            fijarTetromino();
            crearTetromino();
        } else {
            for(Rectangle r : bloquesTetromino){
                r.setY(r.getY() + 30);
            }
        }
    }
    
    private void fijarTetromino(){
        for(Rectangle r : bloquesTetromino){
            int fila = (int) (r.getY() / 30);
            int columna = (int) (r.getX() / 30);
            tablero[fila][columna] = 1;
        }
    }

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
            boolean puedeMoverse = true;
            switch (event.getCode()) {
                case LEFT -> {
                    for (Rectangle r : bloquesTetromino){
                        int nuevaCol = (int) ((r.getX() -30)/30);
                        int fila = (int) (r.getY() / 30);
                        
                        if (nuevaCol < 0 || tablero[fila][nuevaCol] == 1){
                            puedeMoverse = false;
                            break;
                        }
                    }
                    
                    if(puedeMoverse){
                        for (Rectangle r : bloquesTetromino){
                            r.setX(r.getX() - 30);
                        }
                    }
                }
                case RIGHT -> {
                    for (Rectangle r : bloquesTetromino){
                        int nuevaCol = (int)((r.getX() + 30)/30);
                        int fila = (int) (r.getY() / 30);
                        
                        if (nuevaCol > 9 || tablero[fila][nuevaCol] == 1) {
                            puedeMoverse = false;
                            break;
                        }
                    }
                    if (puedeMoverse) {
                        for (Rectangle r : bloquesTetromino){
                            r.setX(r.getX() + 30);
                        }
                    }
                }
                case DOWN -> puedeBajar();
            }
        });

        gamePane.setFocusTraversable(true);
    }

    private void crearTetromino() {
        Tetromino actual = FabricaTetrominos.formaAleatoria();
        int[][] forma = actual.getForma();
        int index = 0;
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[0].length; j++) {
                if (forma[i][j] != 0) {
                    Rectangle bloque = new Rectangle(30, 30);
                    bloque.setX((baseX + i) * 30);
                    bloque.setY((baseY + j) * 30);
                    bloque.setFill(colorPorCodigo(forma[i][j]));
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
