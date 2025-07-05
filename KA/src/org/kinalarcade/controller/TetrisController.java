package org.kinalarcade.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.R;
import static javafx.scene.input.KeyCode.RIGHT;
import javafx.scene.layout.AnchorPane;
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
    private Tetromino actual, siguienteTetromino;
    private int filasCompletas = 0;
    private int puntos = 0;
    private double velocidad = 0.5;

    @FXML
    private Button btnRendirse;

    @FXML
    private Button btnRegresar;
    
    @FXML
    private TextField txtPuntos;

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    public TextField getTxtPuntos() {
        return txtPuntos;
    }
    

    @FXML
    private Pane gamePane, proximoTetromino;
    private Timeline timeline;
    
    @FXML
    private AnchorPane anchor;

    // Metodo para que se pueda mover automaticamente el tetromino mediante Timeline.
    private void movimientoAutomatico() {
        crearTetromino();
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.50), event -> {
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
            bloquesVisibles[fila][columna] = r;
        }
    }

    // Metodo para asignar color al Tetromino
    private Color colorPorCodigo(int code) {
        switch (code) {
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.RED;
            case 3:
                return Color.LIGHTGREEN;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.SKYBLUE;
            case 6:
                return Color.LIGHTBLUE;
            default:
                return Color.GRAY;
        }
    }

    private Color bordes(int code) {
        switch (code) {
            case 1:
                return Color.DARKGOLDENROD;
            case 2:
                return Color.DARKRED;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.DARKMAGENTA;
            case 5:
                return Color.BLUE;
            case 6:
                return Color.DARKBLUE;
            default:
                return Color.GRAY;
        }
    }

    // Metodo para poder mover el tetromino mediante la deteccion de eventos de teclado.
    private void moverRectangulo() {
        gamePane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R -> {
                    int[][] formaRotada = actual.rotarTetromino(actual.getForma());
                    if (validarColisiones(formaRotada, baseX, baseY)) {
                        actual.setForma(formaRotada);
                        actualizarVista(formaRotada);
                    }
                    break;
                }
                case A -> {
                    if (validarColisiones(actual.getForma(), baseX - 1, baseY)) {
                        baseX--;
                        actualizarVista(actual.getForma());
                    }
                    break;
                }
                case D -> {
                    if (validarColisiones(actual.getForma(), baseX + 1, baseY)) {
                        baseX++;
                        actualizarVista(actual.getForma());
                    }
                    break;
                }
                case S -> {
                    if (validarColisiones(actual.getForma(), baseX, baseY + 1)) {
                        baseY++;
                        actualizarVista(actual.getForma());
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

    public void comprobarFilaCompleta() {
        int contadorFilas = 0;
        for (int fila = 19; fila >= 0; fila--) {
            boolean completa = true;
            for (int col = 0; col < 10; col++) {
                if (tablero[fila][col] == 0) {
                    completa = false;
                    break;
                }
            }

            if (completa) {  
                eliminarFila(fila);
                contadorFilas++;
                fila++;
            }
        }
        
        if (contadorFilas > 0){
            filasCompletas += contadorFilas;
            puntos += calcularPuntos(contadorFilas);
            txtPuntos.setText(String.valueOf(puntos));
            
            if (filasCompletas % 5 == 0){
                aumentarVelocidad();
            }
        }
    }
    
    private void aumentarVelocidad(){
        if (velocidad > 0.05){
            velocidad -= 0.2;
            timeline.stop();
            timeline = new Timeline(new KeyFrame(Duration.seconds(velocidad), event -> {
                puedeBajar();
            }));
            timeline.setCycleCount(timeline.INDEFINITE);
            timeline.play();
        }
    }
    
    private int calcularPuntos(int filas){
        switch (filas) {
            case 1: return 100;
            case 2: return 300;
            case 3: return 500;
            case 4: return 800;
            default: return 0;
        }
    }

    private void eliminarFila(int fila) {
        for (int col = 0; col < 10; col++) {
            Rectangle bloque = bloquesVisibles[fila][col];
            if (bloque != null) {
                gamePane.getChildren().remove(bloque);
                bloquesVisibles[fila][col] = null;
            }
            tablero[fila][col] = 0;
        }

        for (int f = fila - 1; f >= 0; f--) {
            for (int c = 0; c < 10; c++) {
                tablero[f + 1][c] = tablero[f][c];
                bloquesVisibles[f + 1][c] = bloquesVisibles[f][c];

                if (bloquesVisibles[f][c] != null) {
                    bloquesVisibles[f][c].setY((f + 1) * 30);
                }

                tablero[f][c] = 0;
                bloquesVisibles[f][c] = null;
            }
        }
    }

    private void crearTetromino() {
        if (siguienteTetromino == null) {
            siguienteTetromino = FabricaTetrominos.formaAleatoria();
        }

        actual = siguienteTetromino;
        siguienteTetromino = FabricaTetrominos.formaAleatoria();

        int[][] forma = actual.getForma();
        int index = 0;
        baseX = 3;
        baseY = 0;

        if (!validarColisiones(forma, baseX, baseY)) {
            timeline.stop();
            principal.gameOver(puntos);
            return;
        }

        bloquesTetromino = new Rectangle[4];

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[0].length; j++) {
                if (forma[i][j] != 0) {
                    Rectangle bloque = new Rectangle(30, 30);
                    bloque.setX((baseX + j) * 30);
                    bloque.setY((baseY + i) * 30);
                    bloque.setFill(colorPorCodigo(forma[i][j]));
                    bloque.setStroke(bordes(forma[i][j]));
                    bloquesTetromino[index++] = bloque;
                    gamePane.getChildren().add(bloque);
                }
            }
        }

        mostrarProximoTetromino();
    }

    private void mostrarProximoTetromino() {
        proximoTetromino.getChildren().clear();

        int[][] forma = siguienteTetromino.getForma();
        int tetrominotamaño = 30;

        int filasOcupadas = 0;
        int columnasOcupadas = 0;

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[0].length; j++) {
                if (forma[i][j] != 0) {
                    if (i + 1 > filasOcupadas) {
                        filasOcupadas = i + 1;
                    }
                    if (j + 1 > columnasOcupadas) {
                        columnasOcupadas = j + 1;
                    }
                }
            }
        }

        double paneAncho = proximoTetromino.getPrefWidth();
        double paneAlto = proximoTetromino.getPrefHeight();

        double figuraAncho = columnasOcupadas * tetrominotamaño;
        double figuraAlto = filasOcupadas * tetrominotamaño;

        double margenIzquierdo = (paneAncho - figuraAncho) / 2;
        double margenSuperior = (paneAlto - figuraAlto) / 2;

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[0].length; j++) {
                if (forma[i][j] != 0) {
                    Rectangle bloque = new Rectangle(tetrominotamaño, tetrominotamaño);
                    bloque.setX(margenIzquierdo + j * tetrominotamaño);
                    bloque.setY(margenSuperior + i * tetrominotamaño);
                    bloque.setFill(colorPorCodigo(forma[i][j]));
                    bloque.setStroke(bordes(forma[i][j]));
                    proximoTetromino.getChildren().add(bloque);
                }
            }
        }
    }

    @FXML
    private void rendirse(ActionEvent evento) {
        if (evento.getSource() == btnRendirse) {
            principal.gameOver(puntos);
            timeline.stop();
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
