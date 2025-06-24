package org.alessandrozac.model;

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
      
}
