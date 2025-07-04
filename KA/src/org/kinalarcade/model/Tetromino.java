
package org.kinalarcade.model;

/**
 *
 * @author Zacarias
 */
public class Tetromino {
    int[][] forma;
    int codigoColor;

    public Tetromino(int[][] forma, int codigoColor) {
        this.forma = forma;
        this.codigoColor = codigoColor;
    }

    public int[][] getForma() {
        return forma;
    }
    
    public int getCodigoColor() {
        return codigoColor;
    }

    public void setForma(int[][] nuevaForma) {
        this.forma = nuevaForma;
    }
    
    public int[][] rotarTetromino(int[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] rotada = new int[columnas][filas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                rotada[j][filas - 1 -i] = matriz[i][j];
            }
        }
        return rotada;
    }
      
}
