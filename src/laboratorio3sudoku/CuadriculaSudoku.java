/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio3sudoku;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class CuadriculaSudoku {
    private CeldaSudoku[][] cuadricula;

    public CuadriculaSudoku() {
        cuadricula = new CeldaSudoku[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                cuadricula[fila][columna] = new CeldaSudoku(0, true);
            }
        }
        rellenarValoresAleatorios();
    }

    private void rellenarValoresAleatorios() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {  // Genera 20 valores aleatorios
            int fila = random.nextInt(9);
            int columna = random.nextInt(9);
            int valor = random.nextInt(9) + 1;
            if (esValido(fila, columna, valor)) {
                cuadricula[fila][columna].setValor(valor);
                cuadricula[fila][columna].setEditable(false);
            }
        }
    }

    public CeldaSudoku getCelda(int fila, int columna) {
        return cuadricula[fila][columna];
    }

    public void setCelda(int fila, int columna, int valor) {
        CeldaSudoku celda = cuadricula[fila][columna];
        if (celda.esEditable()) {
            celda.setValor(valor);
        }
    }

    public boolean esValido(int fila, int columna, int valor) {
        // Verificar si el valor es válido en la fila y columna
        for (int i = 0; i < 9; i++) {
            if (i != columna && cuadricula[fila][i].getValor() == valor) {
                return false;
            }
            if (i != fila && cuadricula[i][columna].getValor() == valor) {
                return false;
            }
        }
        // Verificar si el valor es válido en la subcuadrícula 3x3
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((inicioFila + i != fila || inicioColumna + j != columna) &&
                    cuadricula[inicioFila + i][inicioColumna + j].getValor() == valor) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estaCompletoYValido() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                int valor = cuadricula[fila][columna].getValor();
                if (valor == 0 || !esValido(fila, columna, valor)) {
                    return false;
                }
            }
        }
        return true;
    }
}