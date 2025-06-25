package org.kinalarcade.model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class TableroBuscamina {

    public Casilla[][] casillas;

    public int numFilas;
    public int numColumnas;
    private int numMinas;

    int numCasillasAbiertas;
    boolean generacionMinas;

    private Consumer<List<Casilla>> eventoPartidaPerdida;
    private Consumer<List<Casilla>> eventoPartidaGanada;
    private Consumer<Casilla> eventoCasillaAbierta;

    public TableroBuscamina(int numFilas, int numColumnas, int numMinas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numMinas = numMinas;
        this.inicializarCasillas();
        this.generacionMinas = false;
    }

    private void inicializarCasillas() {
        casillas = new Casilla[this.numFilas][this.numColumnas];
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }

    private void generarMinas(int posFilaIgnorar, int posColumnaIgnorar) {
        int minasGeneradas = 0;
        while (minasGeneradas != numMinas) {
            int posTmpFila = (int) (Math.random() * casillas.length);
            int posTmpColumna = (int) (Math.random() * casillas[0].length);

            if ((posTmpFila == posFilaIgnorar && posTmpColumna == posColumnaIgnorar)
                    || casillas[posTmpFila][posTmpColumna].isMina()) {
                continue;
            }

            casillas[posTmpFila][posTmpColumna].setMina(true);
            minasGeneradas++;
        }

        actualizarNumeroMinasAlrededor();
        this.generacionMinas = true;
        imprimirTablero();
    }

    private void imprimirTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].isMina() ? "*" : "0");
            }
            System.out.println("");
        }
    }

    private void actualizarNumeroMinasAlrededor() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].isMina()) {
                    List<Casilla> alrededor = obtenerCasillasAlrededor(i, j);
                    alrededor.forEach(Casilla::incrementarNumeroMinasAlrededor);
                }
            }
        }
    }

    private List<Casilla> obtenerCasillasAlrededor(int fila, int columna) {
        List<Casilla> lista = new LinkedList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                if (nuevaFila >= 0 && nuevaFila < numFilas && nuevaColumna >= 0 && nuevaColumna < numColumnas) {
                    if (nuevaFila != fila || nuevaColumna != columna) {
                        lista.add(casillas[nuevaFila][nuevaColumna]);
                    }
                }
            }
        }
        return lista;
    }

    public void seleccionarCasilla(int fila, int columna) {
        if (!generacionMinas) {
            generarMinas(fila, columna);
        }

        Casilla casilla = casillas[fila][columna];

        if (casilla.isAbierta()) {
            return;
        }

        casilla.setAbierta(true);

        if (casilla.isMina()) {
            // Si es mina, mostrar todas las minas y terminar el juego
            if (eventoPartidaPerdida != null) {
                eventoPartidaPerdida.accept(obtenerCasillasConMinas());
            }
            return;
        }

        numCasillasAbiertas++;

        if (eventoCasillaAbierta != null) {
            eventoCasillaAbierta.accept(casilla);
        }

        if (casilla.getNumMinasAlrededor() == 0) {
            for (Casilla c : obtenerCasillasAlrededor(fila, columna)) {
                if (!c.isAbierta()) {
                    seleccionarCasilla(c.getPosFila(), c.getPosColumna());
                }
            }
        }

        if (partidaGanada() && eventoPartidaGanada != null) {
            eventoPartidaGanada.accept(obtenerCasillasConMinas());
        }
    }

    private List<Casilla> obtenerCasillasConMinas() {
        List<Casilla> lista = new LinkedList<>();
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].isMina()) {
                    lista.add(casillas[i][j]);
                }
            }
        }
        return lista;
    }

    public boolean partidaGanada() {
        return numCasillasAbiertas >= (numFilas * numColumnas - numMinas);
    }

    public void setEventoPartidaPerdida(Consumer<List<Casilla>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;
    }

    public void setEventoCasillaAbierta(Consumer<Casilla> eventoCasillaAbierta) {
        this.eventoCasillaAbierta = eventoCasillaAbierta;
    }

    public void setEventoPartidaGanada(Consumer<List<Casilla>> eventoPartidaGanada) {
        this.eventoPartidaGanada = eventoPartidaGanada;
    }

    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }
}
