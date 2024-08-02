/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio3sudoku;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
/**
 *
 * @author Administrator
 */


public class GUISudoku extends JFrame {
    private JTextField[][] campos;
    private JuegoSudoku juego;

    public GUISudoku(JuegoSudoku juego) {
        this.juego = juego;
        setTitle("Sudoku");
        setSize(600, 700); // Aumentamos el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelCuadricula = new JPanel(new GridLayout(9, 9));
        CuadriculaSudoku cuadricula = juego.getCuadricula();
        campos = new JTextField[9][9];

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                CeldaSudoku celda = cuadricula.getCelda(fila, columna);
                campos[fila][columna] = new JTextField();
                campos[fila][columna].setHorizontalAlignment(JTextField.CENTER);
                campos[fila][columna].setFont(new Font("Arial", Font.PLAIN, 24)); // Aumentamos el tamaño de la fuente

                // Establecemos bordes gruesos para las subcuadrículas 3x3
                Border borde = BorderFactory.createMatteBorder(
                        fila % 3 == 0 ? 2 : 1,
                        columna % 3 == 0 ? 2 : 1,
                        (fila + 1) % 3 == 0 ? 2 : 1,
                        (columna + 1) % 3 == 0 ? 2 : 1,
                        Color.BLACK
                );
                campos[fila][columna].setBorder(borde);

                if (!celda.esEditable()) {
                    campos[fila][columna].setText(String.valueOf(celda.getValor()));
                    campos[fila][columna].setEditable(false);
                    campos[fila][columna].setBackground(Color.LIGHT_GRAY);
                }
                panelCuadricula.add(campos[fila][columna]);
            }
        }

        JPanel panelBotones = new JPanel();
        JButton botonResolver = new JButton("Resolver");
        botonResolver.addActionListener(e -> {
            juego.resolver();
            actualizarCuadricula(juego.getCuadricula());
        });
        panelBotones.add(botonResolver);

        JButton botonVerificar = new JButton("Verificar");
        botonVerificar.addActionListener(e -> {
            actualizarCuadriculaDesdeCampos();
            boolean correcto = juego.getCuadricula().estaCompletoYValido();
            String mensaje = correcto ? "¡Sudoku correcto!" : "Hay errores en el Sudoku.";
            JOptionPane.showMessageDialog(this, mensaje);
        });
        panelBotones.add(botonVerificar);

        JButton botonNuevoJuego = new JButton("Nuevo Juego");
        botonNuevoJuego.addActionListener(e -> {
            juego.nuevoJuego();
            actualizarCuadricula(juego.getCuadricula());
        });
        panelBotones.add(botonNuevoJuego);

        add(panelCuadricula, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarCuadricula(CuadriculaSudoku cuadricula) {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                CeldaSudoku celda = cuadricula.getCelda(fila, columna);
                campos[fila][columna].setText(String.valueOf(celda.getValor()));
                campos[fila][columna].setEditable(celda.esEditable());
                campos[fila][columna].setBackground(celda.esEditable() ? Color.WHITE : Color.LIGHT_GRAY);
            }
        }
    }

    private void actualizarCuadriculaDesdeCampos() {
        CuadriculaSudoku cuadricula = juego.getCuadricula();
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                String texto = campos[fila][columna].getText();
                int valor = texto.isEmpty() ? 0 : Integer.parseInt(texto);
                cuadricula.setCelda(fila, columna, valor);
            }
        }
    }

    public static void main(String[] args) {
        JuegoSudoku juego = new JuegoSudoku();
        GUISudoku gui = new GUISudoku(juego);
        gui.setVisible(true);
    }
}
