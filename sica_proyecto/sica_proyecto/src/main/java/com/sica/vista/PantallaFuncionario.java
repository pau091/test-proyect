package com.sica.vista;

import com.sica.configuracion.ConexionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PantallaFuncionario {

    public void mostrarMenu() {
        String[] opciones = {"Aprobar Visita Excepcional", "Ver Reportes", "Cerrar Sesión"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(
                null, "Panel de Funcionarios", "SICA - Gestión Admin",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]
            );

            if (opcion == 0) {
                aprobarVisitaExcepcional();
            } else if (opcion == 1) {
                mostrarTablaReportes();
            } else {
                break;
            }
        }
    }

    private void aprobarVisitaExcepcional() {
        String documento = JOptionPane.showInputDialog(null, "Ingrese el documento del visitante a autorizar:");
        
        if (documento != null && !documento.trim().isEmpty()) {
            String sqlCheck = "SELECT id FROM personas WHERE documento_identidad = ?";
            
            try (Connection conn = ConexionBD.getInstancia();
                 PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                
                stmtCheck.setString(1, documento.trim());
                ResultSet rs = stmtCheck.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Visita autorizada exitosamente para el documento: " + documento.trim());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ninguna persona registrada con el documento: " + documento.trim(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarTablaReportes() {
        String[] columnas = {"ID Visita", "Nombre", "Documento", "Tipo Persona", "Fecha Entrada", "Fecha Salida", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        String sql = "SELECT v.id, p.nombre, p.documento_identidad, p.tipo_persona, v.fecha_entrada, v.fecha_salida, v.estado " +
                     "FROM visitas v " +
                     "JOIN personas p ON v.persona_id = p.id " +
                     "ORDER BY v.fecha_entrada DESC";

        try (Connection conn = ConexionBD.getInstancia();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("documento_identidad"),
                    rs.getString("tipo_persona"),
                    rs.getTimestamp("fecha_entrada"),
                    rs.getTimestamp("fecha_salida") != null ? rs.getTimestamp("fecha_salida") : "EN CAMPUS",
                    rs.getString("estado")
                };
                model.addRow(fila);
            }

            JTable tabla = new JTable(model);
            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setPreferredSize(new Dimension(750, 320));

            JOptionPane.showMessageDialog(null, scroll, "Reporte de Entradas y Salidas - SICA", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los reportes: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}