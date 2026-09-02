package com.sica.vista;

import com.sica.control.GestorIngresos;
import com.sica.excepcion.ErrorNoEncontrado;
import javax.swing.JOptionPane;

public class PantallaGuarda {
    private GestorIngresos gestorIngresos = new GestorIngresos();

    public void mostrarMenu() {
        String[] opciones = {"Registrar Entrada", "Registrar Salida", "Cerrar Sesión"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(
                null, "Guardias de Seguridad", "SICA - Control de Acceso",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]
            );

            if (opcion == 0) {
                String doc = JOptionPane.showInputDialog("Documento de la persona:");
                if (doc != null && !doc.isBlank()) {
                    try {
                        gestorIngresos.registrarEntrada(doc);
                        JOptionPane.showMessageDialog(null, "¡Entrada registrada exitosamente!");
                    } catch (ErrorNoEncontrado e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (opcion == 1) {
                String doc = JOptionPane.showInputDialog("Documento de la persona:");
                if (doc != null && !doc.isBlank()) {
                    try {
                        gestorIngresos.registrarSalida(doc);
                        JOptionPane.showMessageDialog(null, "¡Salida registrada exitosamente!");
                    } catch (ErrorNoEncontrado e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                break;
            }
        }
    }
}