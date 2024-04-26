package conwaygame.lib;

import conwaygame.lib.Points;

public class Figuras {
    
    /**
     * Metodo para crear figuras en el juego de la vida de Conway. No puede ser una matriz irregular en ambos casos.
     * @param figura figura que se añadira (Matriz cuadrada).
     * @param destino matriz donde se añadira la figura (Matriz cuadrada)
     * @param posicion posicion donde se ingresara la patriz
     * @return devuelve un booleano verificando si se ha podido realizar el metodo.
     */
    public static boolean addFigure(int[][] figura, int[][] destino, Points posicion, boolean inversion){
        int x = posicion.getX();
        int y = posicion.getY();
        //Ver si posicion esta detro de las dimensiones del array destio
        if (x < 0 || x >= destino[0].length || y < 0 || y >= destino.length) {
            return false;
        }
        //verificar si la figura entra en esa posicion.
        if (figura.length >= destino.length - y || figura[0].length >= destino[0].length - x ) {
            return false;
        }

        if (!inversion) {
            for (int i = 0; i < figura.length; i++) {
                for (int j = 0; j < figura[0].length; j++) {
                    destino[y + i][x + j] = figura[i][j];
                }
            }
        }
        else{
            for (int i = 0; i < figura.length; i++) {
                for (int j = 0; j < figura[0].length; j++) {
                    destino[y + i][x + j] = figura[figura.length - i - 1][figura[0].length - j - 1];
                }
            }
        }
        
        return true;
    }
}
