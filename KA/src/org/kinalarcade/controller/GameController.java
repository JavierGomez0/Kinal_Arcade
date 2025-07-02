package org.kinalarcade.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import org.kinalarcade.system.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Label questionLabel, feedbackLabel, scoreLabel;
    @FXML
    private Button trueButton, falseButton, restartButton;

    private List<Question> questions;
    private List<Question> selectedQuestions;
    private int currentIndex = 0;
    private int score = 0;
    private Main principal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadQuestions();  // Mantengo la carga de las preguntas
        restartButton.setDisable(true); // deshabilitado al inicio
        startGame();
    }

    public void setPrincipal(Main principal){
        this.principal = principal;
    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("La capital de Francia es París.", true));
        questions.add(new Question("El sol gira alrededor de la Tierra.", false));
        questions.add(new Question("Java es un lenguaje de programación.", true));
        questions.add(new Question("El agua hierve a 50 grados Celsius.", false));
        questions.add(new Question("La Tierra tiene un satélite natural llamado Luna.", true));
        questions.add(new Question("La Gran Muralla China se puede ver desde la Luna.", false));
        questions.add(new Question("El océano Pacífico es el más grande del mundo.", true));
        questions.add(new Question("Los humanos tienen cinco sentidos principales.", true));
        questions.add(new Question("La fotosíntesis ocurre en los animales.", false));
        questions.add(new Question("El monte Everest es la montaña más alta del mundo.", true));
        questions.add(new Question("La electricidad viaja más rápido que el sonido.", true));
        questions.add(new Question("Los delfines son mamíferos.", true));
        questions.add(new Question("La sangre humana es azul dentro del cuerpo.", false));
        questions.add(new Question("La fórmula del agua es H2O.", true));
        questions.add(new Question("El Titanic se hundió en 1912.", true));
        questions.add(new Question("La Segunda Guerra Mundial terminó en 1945.", true));
        questions.add(new Question("Cristóbal Colón descubrió América en 1492.", true));
        questions.add(new Question("La capital de Japón es Pekín.", false));
        questions.add(new Question("La luna tiene atmósfera densa como la Tierra.", false));
        questions.add(new Question("La velocidad de la luz es mayor que la del sonido.", true));
        questions.add(new Question("Un triángulo tiene cuatro lados.", false));
        questions.add(new Question("El polo norte es más frío que el ecuador.", true));
        questions.add(new Question("Los gatos son herbívoros.", false));
        questions.add(new Question("El ADN es la molécula que contiene la información genética.", true));
        questions.add(new Question("Venus es el planeta más caliente del sistema solar.", true));
        questions.add(new Question("La tabla periódica tiene más de 100 elementos.", true));
        questions.add(new Question("El español se habla en Brasil.", false));
        questions.add(new Question("El cuerpo humano tiene 206 huesos.", true));
        questions.add(new Question("Los murciélagos son ciegos.", false));
        questions.add(new Question("Las plantas convierten dióxido de carbono en oxígeno.", true));
        questions.add(new Question("La Tierra es el tercer planeta desde el Sol.", true));
        questions.add(new Question("La capital de Canadá es Toronto.", false));
        questions.add(new Question("Los peces respiran a través de branquias.", true));
        questions.add(new Question("Albert Einstein fue el inventor de la bombilla.", false));
        questions.add(new Question("El Sahara es un desierto.", true));
        questions.add(new Question("La capital de Argentina es Buenos Aires.", true));
        questions.add(new Question("La fotosíntesis requiere luz solar.", true));
        questions.add(new Question("La energía no se crea ni se destruye, solo se transforma.", true));
        questions.add(new Question("Los camellos almacenan agua en sus jorobas.", false));
        questions.add(new Question("La Gran Barrera de Coral está en Australia.", true));
        questions.add(new Question("El ajedrez se juega con 16 piezas por jugador.", true));
        questions.add(new Question("La gravedad de la Luna es mayor que la de la Tierra.", false));
        questions.add(new Question("La capital de Italia es Roma.", true));
        questions.add(new Question("El símbolo químico del oro es Ag.", false));
        questions.add(new Question("La Antártida es un continente.", true));
        questions.add(new Question("El cuerpo humano tiene tres pulmones.", false));
        questions.add(new Question("La música puede influir en el estado de ánimo.", true));
        questions.add(new Question("El oxígeno es necesario para la respiración humana.", true));
        questions.add(new Question("El código binario solo utiliza 0 y 1.", true));
        questions.add(new Question("El Sol es una estrella.", true));
        questions.add(new Question("La capital de Australia es Sydney.", false));
        questions.add(new Question("Los volcanes pueden estar inactivos durante siglos.", true));
        questions.add(new Question("Las jirafas tienen el cuello corto.", false));
        questions.add(new Question("El ser humano tiene cinco dedos por mano.", true));
        questions.add(new Question("Los rayos X se utilizan en medicina.", true));
        questions.add(new Question("Las ballenas son peces.", false));
        questions.add(new Question("La luz blanca se puede descomponer en colores.", true));
        questions.add(new Question("La capital de Alemania es Berlín.", true));
        questions.add(new Question("Los tomates son vegetales.", false));
        questions.add(new Question("La programación es una habilidad importante en la era digital.", true));
        questions.add(new Question("El polo sur está en el Ártico.", false));
        questions.add(new Question("El cuerpo humano puede vivir sin corazón.", false));
        questions.add(new Question("El ADN se encuentra en el núcleo celular.", true));
        questions.add(new Question("Los océanos cubren más del 70% de la superficie terrestre.", true));
        questions.add(new Question("El uranio es un metal radiactivo.", true));
        questions.add(new Question("El inglés es el idioma oficial en México.", false));
        questions.add(new Question("La capital de Egipto es El Cairo.", true));
        questions.add(new Question("Las estrellas fugaces son meteoros que entran a la atmósfera.", true));
        questions.add(new Question("El azúcar es una proteína.", false));
        questions.add(new Question("La Tierra es plana.", false));
        questions.add(new Question("El hierro es un metal.", true));
        questions.add(new Question("La selva amazónica se encuentra en Sudamérica.", true));
        questions.add(new Question("Las aves tienen dientes.", false));
        questions.add(new Question("El cerebro controla todas las funciones del cuerpo.", true));
        questions.add(new Question("La capital de Rusia es Moscú.", true));
        questions.add(new Question("El agua es un buen conductor eléctrico puro.", false));
        questions.add(new Question("Los seres humanos son omnívoros.", true));
        questions.add(new Question("La electricidad estática es causada por un desequilibrio de cargas eléctricas.", true));
        questions.add(new Question("Los insectos tienen cuatro patas.", false));
        questions.add(new Question("La música clásica fue compuesta principalmente en los siglos XVII a XIX.", true));
        questions.add(new Question("Los glaciares están formados de agua salada.", false));
        questions.add(new Question("Los peces pueden vivir fuera del agua durante semanas.", false));
        questions.add(new Question("La física estudia la materia y la energía.", true));
        questions.add(new Question("La luna llena ocurre cada noche.", false));
        questions.add(new Question("Los antibióticos combaten virus.", false));
        questions.add(new Question("La Tierra tiene estaciones debido a su inclinación axial.", true));
        questions.add(new Question("El ojo humano puede ver luz ultravioleta.", false));
        questions.add(new Question("Las plantas necesitan dióxido de carbono para hacer fotosíntesis.", true));
        questions.add(new Question("Los caballos son rumiantes.", false));
        questions.add(new Question("El sistema solar tiene ocho planetas.", true));
        questions.add(new Question("El hidrógeno es el elemento más ligero.", true));
        questions.add(new Question("Las computadoras funcionan con electricidad.", true));
        questions.add(new Question("El universo tiene un centro definido.", false));
        questions.add(new Question("El oro es un metal precioso.", true));
    }

    private void selectRandomQuestions(int numberOfQuestions) {
        selectedQuestions = new ArrayList<>(questions);
        Collections.shuffle(selectedQuestions); // Sin pasar Random para mayor aleatoriedad
        selectedQuestions = selectedQuestions.subList(0, Math.min(numberOfQuestions, selectedQuestions.size()));
    }

    private void startGame() {
        score = 0;
        currentIndex = 0;
        scoreLabel.setText("Puntaje: " + score);
        restartButton.setDisable(true); // Deshabilitar el botón de reinicio
        selectRandomQuestions(5); // Seleccionamos 5 preguntas aleatorias
        showQuestion();
        trueButton.setDisable(false);
        falseButton.setDisable(false);
    }

    private void showQuestion() {
        if (currentIndex < selectedQuestions.size()) {
            Question q = selectedQuestions.get(currentIndex);
            questionLabel.setText(q.getStatement());
            feedbackLabel.setText("");
            trueButton.setDisable(false);
            falseButton.setDisable(false);
        } else {
            questionLabel.setText("¡Juego terminado! Puntaje final: " + score + " / " + selectedQuestions.size());
            feedbackLabel.setText("");
            trueButton.setDisable(true);
            falseButton.setDisable(true);
            restartButton.setDisable(false); // Habilitar el botón reiniciar
        }
    }

    @FXML
    private void handleTrueButton() {
        checkAnswer(true);
    }

    @FXML
    private void handleFalseButton() {
        checkAnswer(false);
    }

    private void checkAnswer(boolean userAnswer) {
        Question current = selectedQuestions.get(currentIndex);

        if (userAnswer == current.isCorrectAnswer()) {
            score++;
            feedbackLabel.setTextFill(Color.GREEN);
            feedbackLabel.setText("¡Correcto!");
        } else {
            feedbackLabel.setTextFill(Color.RED);
            feedbackLabel.setText("Incorrecto.");
        }

        scoreLabel.setText("Puntaje: " + score);
        trueButton.setDisable(true);
        falseButton.setDisable(true);

        currentIndex++;

        // Esperar 1.5 segundos antes de mostrar la siguiente pregunta
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            javafx.application.Platform.runLater(this::showQuestion);
        }).start();
    }

    @FXML
    private void handleRestartButton() {
        startGame();
    }

    private static class Question {
        private final String statement;
        private final boolean correctAnswer;

        public Question(String statement, boolean correctAnswer) {
            this.statement = statement;
            this.correctAnswer = correctAnswer;
        }

        public String getStatement() {
            return statement;
        }

        public boolean isCorrectAnswer() {
            return correctAnswer;
        }
    }
}
