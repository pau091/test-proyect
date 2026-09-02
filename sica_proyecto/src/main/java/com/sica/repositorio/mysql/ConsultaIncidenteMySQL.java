package com.sica.repositorio.mysql;

import com.sica.configuracion.ConexionBD;
import com.sica.repositorio.interfaces.ConsultaIncidente;

import java.sql.*;

public class ConsultaIncidenteMySQL implements ConsultaIncidente {
    @Override
    public void registrarIncidente(String descripcion, int usuarioId) {
        String sql = "INSERT INTO incidentes (descripcion, fecha, usuario_id) VALUES (?, NOW(), ?)";
        try {
            Connection conn = ConexionBD.getInstancia();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, descripcion);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}