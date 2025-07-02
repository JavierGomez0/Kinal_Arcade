package org.kinalarcade.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kinalarcade.system.main;

public class WordleController implements Initializable {

    private main principal;

    @FXML private TextField entrada;
    @FXML private Label bienvenida;
    @FXML private Label instrucciones;

    @FXML private Label box00, box01, box02, box03, box04;
    @FXML private Label box10, box11, box12, box13, box14;
    @FXML private Label box20, box21, box22, box23, box24;
    @FXML private Label box30, box31, box32, box33, box34;
    @FXML private Label box40, box41, box42, box43, box44;
    @FXML private Label box50, box51, box52, box53, box54;

    @FXML private Button btnMenu;

    private List<List<Label>> gameBoardLabels;

    private final String[] palabrasSecretas = {"GATOS", "PERRO", "MUNDO", "PLATO", "LIBRO", "TECLO", "CHISTE"};
    private String palabraSecreta;
    private int intentoActual = 0;
    private final int maxIntentos = 6;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupGameBoardLabels();
        iniciarJuego();
        entrada.setOnAction(e -> procesarIntento());
    }

    private void setupGameBoardLabels() {
        gameBoardLabels = Arrays.asList(
            Arrays.asList(box00, box01, box02, box03, box04),
            Arrays.asList(box10, box11, box12, box13, box14),
            Arrays.asList(box20, box21, box22, box23, box24),
            Arrays.asList(box30, box31, box32, box33, box34),
            Arrays.asList(box40, box41, box42, box43, box44),
            Arrays.asList(box50, box51, box52, box53, box54)
        );
    }

    private void iniciarJuego() {
        palabraSecreta = palabrasSecretas[(int) (Math.random() * palabrasSecretas.length)];
        intentoActual = 0;
        entrada.setDisable(false);
        entrada.clear();

        bienvenida.setText("¡Bienvenido a Wordle!");
        instrucciones.setText("Adivina la palabra de 5 letras.");

        for (int row = 0; row < maxIntentos; row++) {
            for (int col = 0; col < 5; col++) {
                Label casilla = gameBoardLabels.get(row).get(col);
                if (casilla != null) {
                    casilla.setText("");
                    casilla.getStyleClass().clear();
                    casilla.getStyleClass().add("wordle-box");
                }
            }
        }
    }

    @FXML
    private void procesarIntento() {
        if (intentoActual >= maxIntentos) {
            bienvenida.setText("Juego Terminado.");
            instrucciones.setText("¡Reinicia el juego!");
            entrada.setDisable(true);
            return;
        }

        String intento = entrada.getText().toUpperCase().trim();
        entrada.clear();

        if (intento.length() != 5) {
            bienvenida.setText("Error en el intento.");
            instrucciones.setText("La palabra debe tener 5 letras.");
            return;
        }

        mostrarLetras(intento, palabraSecreta);

        if (intento.equals(palabraSecreta)) {
            bienvenida.setText("¡Correcto!");
            instrucciones.setText("Has adivinado: " + palabraSecreta);
            entrada.setDisable(true);
            return;
        }

        intentoActual++;

        if (intentoActual == maxIntentos) {
            bienvenida.setText("¡Perdiste!");
            instrucciones.setText("La palabra era: " + palabraSecreta + ". ¡Reinicia!");
            entrada.setDisable(true);
        } else {
            bienvenida.setText("Intento #" + (intentoActual + 1));
            instrucciones.setText("Continúa adivinando.");
        }
    }

    private void mostrarLetras(String intento, String secreto) {
        char[] intentoChars = intento.toCharArray();
        char[] secretoChars = secreto.toCharArray();
        String[] resultados = new String[5];

        Arrays.fill(resultados, "gray-box");

        for (int i = 0; i < 5; i++) {
            if (intentoChars[i] == secretoChars[i]) {
                resultados[i] = "green-box";
                secretoChars[i] = '\0';
                intentoChars[i] = '\0';
            }
        }

        for (int i = 0; i < 5; i++) {
            if (intentoChars[i] != '\0') {
                for (int j = 0; j < 5; j++) {
                    if (intentoChars[i] == secretoChars[j]) {
                        resultados[i] = "gold-box";
                        secretoChars[j] = '\0';
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            Label casilla = gameBoardLabels.get(intentoActual).get(i);
            if (casilla == null) {
                System.err.println("Error: La casilla en la fila " + intentoActual + ", columna " + i + " es null.");
                continue;
            }
            casilla.setText(String.valueOf(intento.charAt(i)));
            casilla.getStyleClass().clear();
            casilla.getStyleClass().add(resultados[i]);
        }
    }

    public void setPrincipal(main principal) {
        this.principal = principal;
    }

    @FXML
    private void reiniciarJuego() {
        iniciarJuego();
    }

    @FXML
    public void clicRegresarW(ActionEvent evento){
        if (evento.getSource() == btnMenu){
            System.out.println("Nos vamos al Menu Principal");
            if (principal != null) {
                principal.menuPrincipal();
            }
        }
    }
}