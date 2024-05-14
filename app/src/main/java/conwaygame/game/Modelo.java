package conwaygame.game;

import java.awt.Graphics;

import conwaygame.lib.Figuras;
import conwaygame.lib.Points;

import java.awt.Color;

public class Modelo {
    private int[][] mapPrint;
    private int[][] mapBack;
    private int width;
    private int height;

    private final int widthBlock = 5;
    private final int heightBlock = 5;
    private final int movimentBlock = 5;

    public Modelo(int width, int height) {
        mapPrint = new int[width][height];
        mapBack = new int[width][height];
        restart();
    }

    public void restart(){
        for (int i = 0; i < mapBack.length; i++) {
            for (int j = 0; j < mapBack[i].length; j++) {
                mapBack[i][j] = 0;
            }
        }
         //#region creacion de FIGURAS
       int figura1 [][] = new int[][]{
        {0,1,0},
        {0,0,1},
        {1,1,1}   
        };

        int figura2 [][] = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            {1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            };
        Figuras.addFigure(figura2, mapBack, new Points(2,10), false);
        //Figuras.addFigure(figura2, mapBack, new Points(150,150), true);

       //#endregion
       

    }
    public void updateMap(){

        int filas = mapBack.length;
        int columnas = mapBack[0].length;

        for (int i = 0; i < mapBack.length; i++) {
            for (int j = 0; j < mapBack[i].length; j++) {
                int cont = 0;
                if (i == 0 || j == 0 || i == filas - 1 || j == columnas - 1) {
                    mapBack[i][j] = 0;
                    continue;
                }
                cont += mapBack[i - 1][j - 1];
                cont += mapBack[i][j - 1];
                cont += mapBack[i + 1][j - 1];
                cont += mapBack[i - 1][j];
                cont += mapBack[i + 1][j];
                cont += mapBack[i - 1][j + 1];
                cont += mapBack[i][j + 1];
                cont += mapBack[i + 1][j + 1];

                /*
                cont += mapBack[(i - 1 + filas) % filas][(j - 1 + columnas) % columnas];
                cont += mapBack[i][(j - 1 + columnas) % columnas];
                cont += mapBack[(i + 1) % filas][(j - 1 + columnas) % columnas];
                cont += mapBack[(i - 1 + filas) % filas][j];
                cont += mapBack[(i + 1) % filas][j];
                cont += mapBack[(i - 1 + filas) % filas][(j + 1) % columnas];
                cont += mapBack[i][(j + 1) % columnas];
                cont += mapBack[(i + 1) % filas][(j + 1) % columnas];
                 */
                

                if (mapBack[i][j] == 1) {
                    if (cont == 2 || cont == 3) 
                        mapPrint[i][j] = 1;   
                    else
                        mapPrint[i][j] = 0;
                }
                else{
                    if (cont == 3)
                        mapPrint[i][j] = 1;
                    else
                        mapPrint[i][j] = 0;
                }

            }
        }
        int[][] temp = mapBack;
        mapBack = mapPrint;
        mapPrint = temp;
    }

    public void print(Graphics g){
        for (int i = 0; i < mapBack.length; i++) {
            for (int j = 0; j < mapBack[i].length; j++) {
                if (mapBack[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(i * movimentBlock, j *  movimentBlock, widthBlock, heightBlock);
                }
                else{
                    g.setColor(Color.BLACK);
                    g.fillRect(i * movimentBlock, j * movimentBlock, widthBlock, heightBlock);
                    
                }
            }
        }
    }
}
