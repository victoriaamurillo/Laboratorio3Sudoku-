/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio3sudoku;

/**
 *
 * @author Administrator
 */
public class JuegoSudoku {
    private CuadriculaSudoku cuadricula;
    private SolucionadorSudoku solucionador;

    public JuegoSudoku() {
        cuadricula = new CuadriculaSudoku();
        solucionador = new SolucionadorSudoku(cuadricula);
    }

    public void resolver() {
        solucionador.resolver();
    }

    public CuadriculaSudoku getCuadricula() {
        return cuadricula;
    }

    public void nuevoJuego() {
        cuadricula = new CuadriculaSudoku();
        solucionador = new SolucionadorSudoku(cuadricula);
    }
}