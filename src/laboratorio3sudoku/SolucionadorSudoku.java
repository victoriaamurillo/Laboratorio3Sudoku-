/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio3sudoku;

/**
 *
 * @author Administrator
 */
public class SolucionadorSudoku {
    private CuadriculaSudoku cuadricula;

    public SolucionadorSudoku(CuadriculaSudoku cuadricula) {
        this.cuadricula = cuadricula;
    }

    public boolean resolver() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                if (cuadricula.getCelda(fila, columna).getValor() == 0) {
                    for (int valor = 1; valor <= 9; valor++) {
                        if (cuadricula.esValido(fila, columna, valor)) {
                            cuadricula.setCelda(fila, columna, valor);
                            if (resolver()) {
                                return true;
                            }
                            cuadricula.setCelda(fila, columna, 0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}