package org.alessandrozac.model;

import java.util.Random;

/**
 *
 * @author Zacarias
 */
public class FabricaTetrominos {
    
    public static Tetromino formaAleatoria(){
        Random random = new Random();
        int tipo = random.nextInt(7);
        
        switch (tipo) {
            case 0:
                return new Tetromino(new int[][]{
                    {1,1,1,1}
                }, 1);
            case 1:
                return new Tetromino(new int[][]{
                    {2,2},
                    {2,2}
                },2);
            case 3:
                return new Tetromino(new int[][]{
                    {0,3,0},
                    {3,3,3}
                },3);
            case 4:
                return new Tetromino(new int[][]{
                    {0,4,4},
                    {4,4,0}
                },4);
            case 5:
                return new Tetromino(new int[][]{
                    {5,5,0},
                    {0,5,5}
                },5);
            case 6:
                return new Tetromino(new int[][]{
                    {0,0,6},
                    {6,6,6}
                },6);
            case 7:
                return new Tetromino(new int[][]{
                    {7,0,0},
                    {7,7,7}
                },7);
            default:
                return new Tetromino(new int[][]{
                    {1,1,1,1}
                }, 1);
        }
    }
}
