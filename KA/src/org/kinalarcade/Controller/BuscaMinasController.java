package org.kinalarcade.controller;

import org.kinalarcade.model.TableroBuscamina;
import org.kinalarcade.model.Casilla;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class BuscaMinasController {

    @FXML
    private GridPane tablero;

    @FXML
    private VBox contenedorPrincipal;

    private CheckBox checkMarcarBandera;

    private Button[][] botonesTablero;
    private TableroBuscamina tableroBuscaminas;
    private int numFilas = 10;
    private int numColumnas = 10;
    private int numMinas = 10;

    private int banderasCorrectas = 0;

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void initialize() {
        agregarCheckBoxMarcar();
        crearTablero();
    }

    private void agregarCheckBoxMarcar() {
        checkMarcarBandera = new CheckBox("Marcar bandera");
        contenedorPrincipal.getChildren().add(0, checkMarcarBandera);
    }

    private void crearTablero() {
    botonesTablero = new Button[numFilas][numColumnas];
    tableroBuscaminas = new TableroBuscamina(numFilas, numColumnas, numMinas);
    tablero.getChildren().clear();
    banderasCorrectas = 0;

    for (int i = 0; i < numFilas; i++) {
        for (int j = 0; j < numColumnas; j++) {
            Button btn = new Button();
            btn.setMinSize(40, 40);

            // Estilo de tablero tipo ajedrez
            if ((i + j) % 2 == 0) {
                btn.setStyle("-fx-font-size: 16px; -fx-background-color: white;");
            } else {
                btn.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: white;");
            }

            final int fila = i;
            final int columna = j;

            btn.setOnMouseClicked(event -> {
                if (checkMarcarBandera.isSelected()) {
                    marcarBandera(fila, columna);
                } else {
                    btnClick(fila, columna);
                }
            });

            botonesTablero[i][j] = btn;
            tablero.add(btn, j, i);
        }
    }

    tableroBuscaminas.setEventoPartidaPerdida(casillas -> {
        for (Casilla casilla : casillas) {
            Button btn = botonesTablero[casilla.getPosFila()][casilla.getPosColumna()];
            btn.setText("💥");
        }
        desactivarTablero();
        mostrarMensajePerdida();
    });

    tableroBuscaminas.setEventoPartidaGanada(casillas -> {
        for (Casilla casilla : casillas) {
            botonesTablero[casilla.getPosFila()][casilla.getPosColumna()].setText("🚩");
        }
        desactivarTablero();
        mostrarMensaje("¡Has ganado!", Alert.AlertType.INFORMATION);
    });

    tableroBuscaminas.setEventoCasillaAbierta(casilla -> {
        Button btn = botonesTablero[casilla.getPosFila()][casilla.getPosColumna()];
        btn.setDisable(true);
        btn.setText(casilla.getNumMinasAlrededor() == 0 ? "" : casilla.getNumMinasAlrededor() + "");
        verificarVictoriaPorMarcado();
    });
}
 

    private void btnClick(int fila, int columna) {
        tableroBuscaminas.seleccionarCasilla(fila, columna);
    }

    private void marcarBandera(int fila, int columna) {
        Casilla casilla = tableroBuscaminas.getCasilla(fila, columna);
        Button btn = botonesTablero[fila][columna];
        if (!btn.isDisable()) {
            String texto = btn.getText();
            if ("🚩".equals(texto)) {
                btn.setText("");
                if (casilla.isMina()) {
                    banderasCorrectas--;
                }
            } else {
                btn.setText("🚩");
                if (casilla.isMina()) {
                    banderasCorrectas++;
                }
            }
        }
        verificarVictoriaPorMarcado();
    }

    private void verificarVictoriaPorMarcado() {
        boolean todasDescubiertas = true;

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                Casilla casilla = tableroBuscaminas.getCasilla(i, j);
                Button btn = botonesTablero[i][j];

                if (!casilla.isMina() && !btn.isDisable()) {
                    todasDescubiertas = false;
                    break;
                }
            }
        }

        if (banderasCorrectas == numMinas && todasDescubiertas) {
            desactivarTablero();
            mostrarMensaje("¡Has ganado marcando todas las minas!", AlertType.INFORMATION);
        }
    }

    private void desactivarTablero() {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                botonesTablero[i][j].setDisable(true);
            }
        }
    }

    private void mostrarMensaje(String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Resultado del Juego");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensajePerdida() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("¡Has pisado una mina!");
        alert.setContentText("¿Qué deseas hacer?");

        ButtonType reintentar = new ButtonType("Reintentar");
        ButtonType cerrar = new ButtonType("Cerrar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(reintentar, cerrar);

        alert.showAndWait().ifPresent(respuesta -> {
            if (respuesta == reintentar) {
                crearTablero();
            } else if (respuesta == cerrar && primaryStage != null) {
                primaryStage.close();
            }
        });
    }

    @FXML
    private void nuevoJuego(ActionEvent event) {
        crearTablero();
    }

    @FXML
    public void volverAlMenu(ActionEvent event) {
        if (primaryStage != null) {
            primaryStage.close();
        }
    }

    @FXML
    public void cambiarTamano(ActionEvent event) {
        String input = new TextInputDialog().showAndWait().orElse("10");
        try {
            int nuevoTamano = Integer.parseInt(input);
            if (nuevoTamano > 0) {
                numFilas = nuevoTamano;
                numColumnas = nuevoTamano;
                crearTablero();
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor ingrese un número válido.", AlertType.ERROR);
        }
    }

    @FXML
    private void cambiarNumMinas(ActionEvent event) {
        String input = new TextInputDialog().showAndWait().orElse("10");
        try {
            int nuevasMinas = Integer.parseInt(input);
            if (nuevasMinas >= 0) {
                numMinas = nuevasMinas;
                crearTablero();
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor ingrese un número válido.", AlertType.ERROR);
        }
    }
}
